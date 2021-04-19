package com.growfin.ticketingSystem.services;

public interface EmailService {
	
	void sendTextEmail(String from, String to, String subject, String body);

}
