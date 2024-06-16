package com.growfin.ticketingSystem.services;

import com.growfin.ticketingSystem.models.Administrator;
import com.growfin.ticketingSystem.models.Organization;
import com.growfin.ticketingSystem.models.payloads.SignUpRequest;
import com.growfin.ticketingSystem.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Administrator createAdmin(SignUpRequest signUpRequest) {
        Organization organization = new Organization();
        organization.setId(signUpRequest.getOrganizationId());
        
        Administrator admin = new Administrator();
        admin.setFirstName(signUpRequest.getFirstName());
        admin.setLastName(signUpRequest.getLastName());
        admin.setEmail(signUpRequest.getEmail());
        admin.setPassword(signUpRequest.getPassword()); 
        admin.setOrganization(organization);
        admin.setPhoneNumber(signUpRequest.getPhoneNumber());
        admin.setAddress(signUpRequest.getAddress());
        admin.setStatus("active");

        return administratorRepository.save(admin);
    }

    @Override
    public Administrator findAvailableAdmin(String orgId, String status) {
        String adminId = administratorRepository.findAvailableAdmin(orgId, status);
        return administratorRepository.findById(adminId).orElse(null);
    }

    @Override
    public Administrator findFirstAdminstrator(String orgId) {
        return administratorRepository.findFirstAdminstrator(orgId);
    }

    @Override
    public Optional<Administrator> findById(String id) {
        return administratorRepository.findById(id);
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
    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }
}
