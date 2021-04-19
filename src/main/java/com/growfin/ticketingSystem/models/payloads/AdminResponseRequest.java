package com.growfin.ticketingSystem.models.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdminResponseRequest {
	
	@NotBlank
    @Size(min = 4, max = 40)
    private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
