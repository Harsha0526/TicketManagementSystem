package com.growfin.ticketingSystem.services;

import java.util.List;
import java.util.Optional;

import com.growfin.ticketingSystem.models.Administrator;
import com.growfin.ticketingSystem.models.payloads.SignUpRequest;

public interface AdministratorService {

	Administrator createAdmin(SignUpRequest signUpRequest);

	Administrator findAvailableAdmin(String orgId, String status);

	Administrator findFirstAdminstrator(String orgId);

	Optional<Administrator> findById(String id);

	Boolean existsByEmail(String email);

	Administrator findByEmail(String email);

	List<Administrator> findAll();

}
