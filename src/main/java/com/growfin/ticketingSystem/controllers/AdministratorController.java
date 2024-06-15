package com.growfin.ticketingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.growfin.ticketingSystem.services.AdministratorService;
import com.growfin.ticketingSystem.models.Administrator;
import com.growfin.ticketingSystem.models.payloads.SignUpRequest;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admins")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping()
    private ResponseEntity<?> getAllAdministrators() {
        try {
            return new ResponseEntity<>(administratorService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching administrators.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping()
    private ResponseEntity<?> createAdministrator(@RequestBody SignUpRequest signUpRequest) {
        try {
            Administrator createdAdmin = administratorService.createAdmin(signUpRequest);
            return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the administrator.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateAdministrator(@PathVariable String id, @RequestBody Administrator administrator) {
        try {
            administrator.setId(id);
            Administrator updatedAdmin = administratorService.updateAdministrator(administrator);
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the administrator.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
