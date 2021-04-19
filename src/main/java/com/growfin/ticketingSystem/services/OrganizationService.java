package com.growfin.ticketingSystem.services;

import java.util.Optional;

import com.growfin.ticketingSystem.models.Organization;
import com.growfin.ticketingSystem.models.payloads.OrganizationRequest;

public interface OrganizationService {

	Organization upsertOrg(OrganizationRequest organizationRequest);

	Optional<Organization> findById(String id);

	Organization findOrgBySecret(String secret);

}
