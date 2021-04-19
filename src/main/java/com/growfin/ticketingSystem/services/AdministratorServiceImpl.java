package com.growfin.ticketingSystem.services;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.growfin.ticketingSystem.models.Administrator;
import com.growfin.ticketingSystem.models.Organization;
import com.growfin.ticketingSystem.models.payloads.SignUpRequest;
import com.growfin.ticketingSystem.repositories.AdministratorRepository;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	OrganizationService organizationService;

	@Override
	public Administrator createAdmin(SignUpRequest signUpRequest) {

		Organization organization = organizationService.findOrgBySecret(signUpRequest.getSecret());
		assertTrue("Invalid Org ID, Secret Mapping", organization != null);

		Administrator admin = new Administrator(signUpRequest.getFirstName(), signUpRequest.getLastName(),
				signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()), organization);

		return administratorRepository.save(admin);
	}

	@Override
	public Boolean existsByEmail(String email) {

		return administratorRepository.existsByEmail(email);
	}

	@Override
	public Administrator findByEmail(String email) {

		return administratorRepository.findByEmail(email);
	}

	@Override
	public Optional<Administrator> findById(String id) {
		return administratorRepository.findById(id);
	}

	@Override
	public List<Administrator> findAll() {
		return administratorRepository.findAll();
	}

	@Override
	public Administrator findAvailableAdmin(String orgId, String status) {

		String adminId = administratorRepository.findAvailableAdmin(orgId, status);

		Administrator admin = null;

		if (adminId == null) {
			admin = findFirstAdminstrator(orgId);
		} else {
			admin = administratorRepository.findById(adminId).get();
		}

		return admin;
	}

	@Override
	public Administrator findFirstAdminstrator(String orgId) {
		return administratorRepository.findFirstAdminstrator(orgId);
	}

}
