package com.growfin.ticketingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.growfin.ticketingSystem.services.AdministratorService;

@Controller
@RequestMapping("/admins")
public class AdministratorController {
	
	
	@Autowired
	private AdministratorService administratorService;
	
	
	@GetMapping()
	private ResponseEntity<?> getAdmin() {
		
		return new ResponseEntity<>(administratorService.findAll(),
				HttpStatus.OK);
		
	}
	
	

}
