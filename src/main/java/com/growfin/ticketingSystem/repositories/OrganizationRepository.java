package com.growfin.ticketingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.growfin.ticketingSystem.models.Organization;


@Repository("organizationRepository")
public interface OrganizationRepository extends JpaRepository<Organization, String> {
	
	@Query(nativeQuery = true, value = "SELECT org.* FROM organizations org join organization_secret_mappings org_mapping on org.secret_mapping_id = org_mapping.id WHERE org_mapping.secret= (:secret)")
	Organization findOrgBySecret(@Param("secret") String secret);

}