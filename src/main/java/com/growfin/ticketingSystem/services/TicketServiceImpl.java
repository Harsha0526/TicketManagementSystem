package com.growfin.ticketingSystem.services;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growfin.ticketingSystem.models.AdminResponse;
import com.growfin.ticketingSystem.models.Administrator;
import com.growfin.ticketingSystem.models.Organization;
import com.growfin.ticketingSystem.models.Ticket;
import com.growfin.ticketingSystem.models.payloads.AdminResponseRequest;
import com.growfin.ticketingSystem.models.payloads.TicketStatus;
import com.growfin.ticketingSystem.models.payloads.TicketUpdateRequest;
import com.growfin.ticketingSystem.models.payloads.UserTicketRequest;
import com.growfin.ticketingSystem.repositories.AdminResponseRepository;
import com.growfin.ticketingSystem.repositories.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	AdministratorService administratorService;

	@Autowired
	OrganizationService organizationService;

	@Autowired
	private JobScheduler jobScheduler;

	@Autowired
	AdminResponseRepository adminResponseRepository;

	@Autowired
	EmailService emailService;

	@Override
	public Ticket createTicket(UserTicketRequest userTicketRequest) {

		Organization organization = organizationService.findOrgBySecret(userTicketRequest.getSecret());
		assertTrue("Invalid Org ID, Secret Mapping", organization != null);

		Administrator adminToBeAssigned = administratorService.findAvailableAdmin(organization.getId(),
				TicketStatus.OPEN.toString());

		Optional<Organization> org = organizationService.findById(organization.getId());
		assertTrue("Invalid Org ID", org.isPresent());

		Ticket ticket = new Ticket(userTicketRequest.getDescription(), userTicketRequest.getTitle(),
				TicketStatus.OPEN.toString().toString(), adminToBeAssigned, org.get(), userTicketRequest.getAddedBy());

		return upsertTicket(ticket);
	}

	@Override
	public Ticket updateStatus(String ticketId, String status) {
		Ticket ticket = ticketRepository.findById(ticketId).get();

		assertTrue(TicketStatus.valueOf(status) != null);

		ticket.setStatus(TicketStatus.valueOf(status).toString());

		return upsertTicket(ticket);
	}

	@Override
	public Ticket updateTicket(String ticketId, TicketUpdateRequest ticketUpdateRequest) {

		Optional<Administrator> adminIdToBeAssigned = administratorService.findById(ticketUpdateRequest.getAdminId());
		assertTrue("Invalid Admin_id, Admin Not Found", adminIdToBeAssigned.isPresent());

		Optional<Organization> org = organizationService.findById(ticketUpdateRequest.getOrgId());
		assertTrue("Invalid Org ID", org.isPresent());
		
		Ticket persistedTicket = ticketRepository.findById(ticketId).get();

		Ticket ticket = new Ticket(ticketId, ticketUpdateRequest.getDescription(), ticketUpdateRequest.getTitle(),
				TicketStatus.valueOf(ticketUpdateRequest.getStatus()).toString(), adminIdToBeAssigned.get(), org.get());
		
		persistedTicket.setOrganization(ticket.getOrganization());
		persistedTicket.setDescription(ticket.getDescription());
		persistedTicket.setTitle(ticket.getTitle());
		persistedTicket.setAdministrator(ticket.getAdministrator());
		persistedTicket.setStatus(ticket.getStatus());

		return upsertTicket(persistedTicket);

	}

	@Override
	public Optional<Ticket> findById(String ticketId) {

		return ticketRepository.findById(ticketId);

	}

	@Override
	public Ticket upsertTicket(Ticket ticket) {

		if (ticket.getId() != null) {

			if (ticket.getStatus() == TicketStatus.CLOSED.toString()) {

				ticket.setClosedAt(new Date());

			}
			if (ticket.getStatus() == TicketStatus.RESOLVED.toString()) {

				scheduleAJob(ticket.getId(), TicketStatus.CLOSED.toString());

			}
		}

		return ticketRepository.save(ticket);

	}

	private void scheduleAJob(String ticketId, String newStatus) {

		jobScheduler.schedule(() -> updateStatus(ticketId, newStatus), LocalDateTime.now().plusSeconds(30));

	}

	@Override
	public AdminResponse addResponseToTicket(String ticketId, AdminResponseRequest adminResponseRequest) {

		Ticket ticket = ticketRepository.findById(ticketId).get();

		AdminResponse adminResponse = new AdminResponse(ticket, adminResponseRequest.getMessage());
		
		AdminResponse persistedAdminResponse = adminResponseRepository.save(adminResponse);

		emailService.sendTextEmail("yogesh@sinecycle.com", ticket.getAddedBy(), "Reply to your Ticket",
				adminResponseRequest.getMessage());
		
		updateStatus(ticketId, TicketStatus.WAITING_ON_CUSTOMER.toString());

		return persistedAdminResponse;
	}

	@Override
	public List<Ticket> getTicketByQueryParam(String assingedTo, String status, String customer) {
		List<Ticket> tickets = ticketRepository.getTicketByQueryParam(assingedTo, status, customer);
		return tickets;
	}

}
