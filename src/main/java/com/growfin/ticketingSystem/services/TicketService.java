package com.growfin.ticketingSystem.services;

import java.util.List;
import java.util.Optional;

import com.growfin.ticketingSystem.models.AdminResponse;
import com.growfin.ticketingSystem.models.Ticket;
import com.growfin.ticketingSystem.models.payloads.AdminResponseRequest;
import com.growfin.ticketingSystem.models.payloads.TicketUpdateRequest;
import com.growfin.ticketingSystem.models.payloads.UserTicketRequest;


public interface TicketService {
	
	Ticket createTicket(UserTicketRequest userTicketRequest);

	Ticket updateStatus(String ticketId, String Status);

	Ticket updateTicket(String ticketId, TicketUpdateRequest ticket);

	Optional<Ticket> findById(String ticketId);

	Ticket upsertTicket(Ticket ticket);
	
	List<Ticket> getTicketByQueryParam(String addedBy, String status, String customer);
	
	AdminResponse addResponseToTicket(String ticketId, AdminResponseRequest adminResponseRequest );
	
}
