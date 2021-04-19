package com.growfin.ticketingSystem.models.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserTicketRequest {

	@NotBlank
	@Size(min = 1, max = 500, message = "Max description 500 characters")
	private String description;

	@NotBlank
	@Size(min = 1, max = 50, message = "Max title 50 characters")
	private String title;

	@NotBlank
    @Size(max = 100)
    private String secret;

	@NotBlank
	@Email
	private String addedBy;

	public UserTicketRequest() {
		super();
	}

	public UserTicketRequest(
			@NotBlank @Size(min = 1, max = 500, message = "Max description 500 characters") String description,
			@NotBlank @Size(min = 1, max = 50, message = "Max title 50 characters") String title,
			@NotBlank @Size(max = 100) String secret, @NotBlank @Email String addedBy) {
		super();
		this.description = description;
		this.title = title;
		this.secret = secret;
		this.addedBy = addedBy;
	}


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


	public String getSecret() {
		return secret;
	}


	public void setSecret(String secret) {
		this.secret = secret;
	}


	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

}
