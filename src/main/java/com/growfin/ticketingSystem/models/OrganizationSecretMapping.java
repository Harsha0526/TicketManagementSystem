package com.growfin.ticketingSystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.growfin.ticketingSystem.models.payloads.DateAudit;


@Entity
@Table(name = "organizationSecretMappings", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "secret" }) })
public class OrganizationSecretMapping extends DateAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id")
	private String id;
	
	@NotNull
	@Size(min = 1, max = 50, message = "max 50 characters")
	@Column(name = "secret")
	private String secret;
	

	public OrganizationSecretMapping() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
	
	
}
