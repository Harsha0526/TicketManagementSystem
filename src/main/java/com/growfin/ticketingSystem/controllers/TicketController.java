package com.growfin.ticketingSystem.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.growfin.ticketingSystem.models.Ticket;
import com.growfin.ticketingSystem.models.payloads.*;
import com.growfin.ticketingSystem.services.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody TicketRequest ticketRequest) {
        try {
            Ticket ticket = ticketService.createTicket(ticketRequest);
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while creating the ticket.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable("id") String ticketId, @RequestBody TicketUpdateRequest ticketUpdateRequest) {
        try {
            Optional<Ticket> ticket = ticketService.findById(ticketId);
            if (ticket.isPresent()) {
                Ticket updatedTicket = ticketService.updateTicket(ticketId, ticketUpdateRequest);
                return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ticket with the given ID not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while updating the ticket.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTicket(@PathVariable("id") String ticketId) {
        try {
            Optional<Ticket> ticket = ticketService.findById(ticketId);
            if (ticket.isPresent()) {
                return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ticket with the given ID not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while fetching ticket details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllByFilter(@RequestParam("adminEmail") String adminEmail, @RequestParam("status") String status, @RequestParam("customerEmail") String customerEmail) {
        try {
            return new ResponseEntity<>(ticketService.getTicketByQueryParam(adminEmail, status, customerEmail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while fetching tickets.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/change_status")
    public ResponseEntity<?> updateStatus(@PathVariable("id") String ticketId, @RequestBody StatusChangeRequest statusChangeRequest) {
        try {
            Ticket updatedTicket = ticketService.updateStatus(ticketId, statusChangeRequest.getStatus());
            return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while changing the ticket status.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/respond")
    public ResponseEntity<?> addResponse(@PathVariable("id") String ticketId, @RequestBody AdminResponseRequest adminResponse) {
        try {
            Optional<Ticket> ticket = ticketService.findById(ticketId);
            if (ticket.isPresent()) {
                Ticket respondedTicket = ticketService.addResponseToTicket(ticketId, adminResponse);
                return new ResponseEntity<>(respondedTicket, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ticket with the given ID not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while responding to the ticket.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
