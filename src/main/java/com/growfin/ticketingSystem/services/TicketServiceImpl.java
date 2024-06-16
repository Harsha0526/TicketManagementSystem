package com.growfin.ticketingSystem.services;

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

/**
 * Implementation of TicketService to manage ticket operations.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private AdminResponseRepository adminResponseRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Ticket createTicket(UserTicketRequest userTicketRequest) {
        Organization organization = organizationService.findOrgBySecret(userTicketRequest.getSecret());
        if (organization == null) {
            throw new IllegalArgumentException("Invalid Org ID, Secret Mapping");
        }

        Administrator adminToBeAssigned = administratorService.findAvailableAdmin(
                organization.getId(), TicketStatus.OPEN.toString());

        Optional<Organization> orgOptional = organizationService.findById(organization.getId());
        if (!orgOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid Org ID");
        }

        Ticket ticket = new Ticket(
                userTicketRequest.getDescription(),
                userTicketRequest.getTitle(),
                TicketStatus.OPEN.toString(),
                adminToBeAssigned,
                orgOptional.get(),
                userTicketRequest.getAddedBy()
        );

        return upsertTicket(ticket);
    }

    @Override
    public Ticket updateStatus(String ticketId, String status) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (!ticketOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid Ticket ID");
        }

        Ticket ticket = ticketOptional.get();
        TicketStatus newStatus = TicketStatus.valueOf(status);
        if (newStatus == null) {
            throw new IllegalArgumentException("Invalid Status");
        }

        ticket.setStatus(newStatus.toString());
        return upsertTicket(ticket);
    }

    @Override
    public Ticket updateTicket(String ticketId, TicketUpdateRequest ticketUpdateRequest) {
        Optional<Administrator> adminOptional = administratorService.findById(ticketUpdateRequest.getAdminId());
        if (!adminOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid Admin ID, Admin Not Found");
        }

        Optional<Organization> orgOptional = organizationService.findById(ticketUpdateRequest.getOrgId());
        if (!orgOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid Org ID");
        }

        Optional<Ticket> persistedTicketOptional = ticketRepository.findById(ticketId);
        if (!persistedTicketOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid Ticket ID");
        }

        Ticket persistedTicket = persistedTicketOptional.get();
        persistedTicket.setDescription(ticketUpdateRequest.getDescription());
        persistedTicket.setTitle(ticketUpdateRequest.getTitle());
        persistedTicket.setAdministrator(adminOptional.get());
        persistedTicket.setOrganization(orgOptional.get());
        persistedTicket.setStatus(TicketStatus.valueOf(ticketUpdateRequest.getStatus()).toString());

        return upsertTicket(persistedTicket);
    }

    @Override
    public Optional<Ticket> findById(String ticketId) {
        return ticketRepository.findById(ticketId);
    }

    @Override
    public Ticket upsertTicket(Ticket ticket) {
        if (ticket.getId() != null) {
            if (TicketStatus.CLOSED.toString().equals(ticket.getStatus())) {
                ticket.setClosedAt(new Date());
            }
            if (TicketStatus.RESOLVED.toString().equals(ticket.getStatus())) {
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
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        if (!ticketOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid Ticket ID");
        }

        Ticket ticket = ticketOptional.get();
        AdminResponse adminResponse = new AdminResponse(ticket, adminResponseRequest.getMessage());

        AdminResponse persistedAdminResponse = adminResponseRepository.save(adminResponse);
        emailService.sendTextEmail("yogesh@sinecycle.com", ticket.getAddedBy(), "Reply to your Ticket", adminResponseRequest.getMessage());

        updateStatus(ticketId, TicketStatus.WAITING_ON_CUSTOMER.toString());

        return persistedAdminResponse;
    }

    @Override
    public List<Ticket> getTicketByQueryParam(String assignedTo, String status, String customer) {
        return ticketRepository.getTicketByQueryParam(assignedTo, status, customer);
    }
}
