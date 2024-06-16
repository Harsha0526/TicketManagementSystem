package com.growfin.ticketingSystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.growfin.ticketingSystem.models.Organization;
import com.growfin.ticketingSystem.models.OrganizationSecretMapping;
import com.growfin.ticketingSystem.models.payloads.OrganizationRequest;
import com.growfin.ticketingSystem.repositories.OrganizationRepository;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	OrganizationSecretMappingService organizationSecretMappingService;

	@Override
	public Organization upsertOrg(OrganizationRequest organizationRequest) {

		OrganizationSecretMapping organizationSecretMapping = organizationSecretMappingService.upsertOrg();
		
		Organization org = new Organization(organizationRequest.getName(), 
		organizationRequest.getDomain(), 
		organizationSecretMapping
		organizationRequest.getAddress(),         
        organizationRequest.getPhoneNumber(),     
        organizationRequest.getStatus()           
		);

		return organizationRepository.save(org);
	}

	@Override
	public Optional<Organization> findById(String id) {

		return organizationRepository.findById(id);
	}


	@Override
	public Organization findOrgBySecret(String secret) {
		
		return organizationRepository.findOrgBySecret(secret);
	}

	@Override
    public Organization updateOrganization(Organization organization) { // New method
        return organizationRepository.save(organization);
    }

}