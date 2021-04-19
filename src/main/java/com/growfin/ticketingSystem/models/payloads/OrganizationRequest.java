package com.growfin.ticketingSystem.models.payloads;

import javax.validation.constraints.NotBlank;

public class OrganizationRequest {

	@NotBlank
	private String name;
	
	@NotBlank
	private String domain;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
}
