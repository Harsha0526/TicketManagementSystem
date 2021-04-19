package com.growfin.ticketingSystem.models.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StatusChangeRequest {

	
	@NotBlank
    @Size(min = 4, max = 40)
    private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
