package com.growfin.ticketingSystem.services;

import java.util.Optional;

import com.growfin.ticketingSystem.models.OrganizationSecretMapping;

public interface OrganizationSecretMappingService {
	
	OrganizationSecretMapping upsertOrg();

	Optional<OrganizationSecretMapping> findById(String id);
	
	boolean existsBySecret(String secret);
}
