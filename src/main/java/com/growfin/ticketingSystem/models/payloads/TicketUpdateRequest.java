package com.growfin.ticketingSystem.models.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TicketUpdateRequest {
	
	@NotBlank
	@Size(min = 1, max = 500, message = "Max description 500 characters")
	private String description;

	@NotBlank
	@Size(min = 1, max = 50, message = "Max title 50 characters")
	private String title;
	
	@NotBlank
	private String adminId;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String orgId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgId() {
		return orgId;
	}

	
	//This is a Hack, this should ideally be taken from Admins principle
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
	
	

}
