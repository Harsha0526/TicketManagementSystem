package com.growfin.ticketingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TicketingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketingSystemApplication.class, args);
	}

}
