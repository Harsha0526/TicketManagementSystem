package com.growfin.ticketingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.growfin.ticketingSystem.models.Organization;
import com.growfin.ticketingSystem.services.OrganizationService;


@Controller
@RequestMapping("/orgs")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	
	
}
