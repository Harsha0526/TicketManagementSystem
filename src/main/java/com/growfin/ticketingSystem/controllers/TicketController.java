package com.growfin.ticketingSystem.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.growfin.ticketingSystem.models.Ticket;
import com.growfin.ticketingSystem.models.payloads.AdminResponseRequest;
import com.growfin.ticketingSystem.models.payloads.StatusChangeRequest;
import com.growfin.ticketingSystem.models.payloads.TicketUpdateRequest;
import com.growfin.ticketingSystem.models.payloads.UserTicketRequest;
import com.growfin.ticketingSystem.services.OrganizationSecretMappingService;
import com.growfin.ticketingSystem.services.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketservice;

	@Autowired
	private OrganizationSecretMappingService organizationSecretMappingService;

	@PostMapping("")
	public ResponseEntity<?> createTicket(@RequestBody UserTicketRequest userTicketRequest) {

		ResponseEntity<?> response;

		if (!organizationSecretMappingService.existsBySecret(userTicketRequest.getSecret())) {

			response = new ResponseEntity<>("Wrong Secrets", HttpStatus.UNAUTHORIZED);

		} else {
			response = new ResponseEntity<>(ticketservice.createTicket(userTicketRequest), HttpStatus.OK);
		}

		return response;
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> updateTicket(@PathVariable("id") String ticketId,
			@RequestBody TicketUpdateRequest ticketUpdateRequest) {

		ResponseEntity<?> response;
		Optional<Ticket> ticket = ticketservice.findById(ticketId);

		if (ticket.isPresent()) {
			response = new ResponseEntity<>(ticketservice.updateTicket(ticketId, ticketUpdateRequest), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>("Ticket with The given Id not found", HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTicket(@PathVariable("id") String ticketId) {

		ResponseEntity<?> response;
		Optional<Ticket> ticket = ticketservice.findById(ticketId);

		if (ticket.isPresent()) {
			response = new ResponseEntity<>(ticket.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>("Ticket with The given Id not found", HttpStatus.NOT_FOUND);
		}

		return response;

	}

	@GetMapping("")
	public ResponseEntity<?> getAllByFilter(@RequestParam("adminEmail") String adminEmail, @RequestParam("status") String status,  @RequestParam("customerEmail") String customerEmail) {

		
		ResponseEntity<?> response = new ResponseEntity<>(ticketservice.getTicketByQueryParam(adminEmail, status, customerEmail), HttpStatus.OK);

		return response;

	}

	@PostMapping("/{id}/change_status")
	public ResponseEntity<Ticket> updateStatus(@PathVariable("id") String ticketId,
			@RequestBody StatusChangeRequest status) {

		return new ResponseEntity<Ticket>(ticketservice.updateStatus(ticketId, status.getStatus()), HttpStatus.OK);

	}

	@PostMapping("/{id}/respond")
	public ResponseEntity<?> addResponse(@PathVariable("id") String ticketId, @RequestBody AdminResponseRequest adminResponse) {
		
		ResponseEntity<?> response;
		Optional<Ticket> ticket = ticketservice.findById(ticketId);
		
		if (!ticket.isPresent()) {
			response = new ResponseEntity<>("Ticket with The given Id not found", HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(ticketservice.addResponseToTicket(ticketId, adminResponse), HttpStatus.OK);
		}
		
		return response;

	}
	
	
	
}
