package com.growfin.ticketingSystem;

import org.junit.jupiter.api.Test;
import com.growfin.ticketingSystem.models.Administrator;
import com.growfin.ticketingSystem.models.Organization;
import com.growfin.ticketingSystem.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TicketingSystemApplicationTests {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Test
    public void testCreateAdministrator() {
        Organization organization = new Organization();
        organization.setName("Test Org");

        Administrator admin = new Administrator();
        admin.setFirstName("John");
        admin.setLastName("Doe");
        admin.setEmail("john.doe@example.com");
        admin.setPassword("password"); 
        admin.setOrganization(organization);
        admin.setPhoneNumber("1234567890");
        admin.setAddress("123 Main St");
        admin.setStatus("active");

        Administrator savedAdmin = administratorRepository.save(admin);
        assertNotNull(savedAdmin);
    }
}
