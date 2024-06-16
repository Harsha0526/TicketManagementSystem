package com.growfin.ticketingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.growfin.ticketingSystem.models.OrganizationSecretMapping;

@Repository
public interface OrganizationSecretMappingRepository extends JpaRepository<OrganizationSecretMapping, String> {

    boolean existsById(String secretId);

    boolean existsBySecret(String secret);
}
