package com.growfin.ticketingSystem.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	SendGrid sendGridClient;

	@Override
	public void sendTextEmail(String from, String to, String subject, String body) {

		sendEmail(from, to, subject, new Content("text/plain", body));

	}

	private Response sendEmail(String from, String to, String subject, Content content) {
		
		Mail mail = new Mail(new Email(from), subject, new Email(to), content);
		
		mail.setReplyTo(new Email("yogesh@sinecycle.com"));
		Request request = new Request();
		Response response = null;
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			this.sendGridClient.api(request);
		} catch (IOException ex) {
	
		}
	    
		return response;
	}

}
