package com.growfin.ticketingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.growfin.ticketingSystem.services.OrganizationService;
import com.growfin.ticketingSystem.models.Organization;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerOrganization(@RequestBody OrganizationRequest organizationRequest) {
        try {
            Organization organization = organizationService.createOrganization(organizationRequest);
            return new ResponseEntity<>(organization, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred during organization registration.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganization(@PathVariable String id) {
        try {
            Organization organization = organizationService.getOrganizationById(id);
            if (organization != null) {
                return new ResponseEntity<>(organization, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Organization not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while fetching organization details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
