package com.growfin.ticketingSystem.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig {

	@Value("${sendgrid.api.key}") 
	String sendGridAPIKey;
	
	@Bean
    public SendGrid getSendgridKey() {
		return new SendGrid(sendGridAPIKey);
	}
  
}
