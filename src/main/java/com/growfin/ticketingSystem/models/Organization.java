package com.growfin.ticketingSystem.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.growfin.ticketingSystem.models.payloads.DateAudit;

@Entity
@Table(name = "organizations")
public class Organization extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	@Size(min = 1, max = 50, message = "max 50 characters")
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(min = 1, max = 50, message = "max 50 characters")
	@Column(name = "domain")
	private String domain;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secret_mapping_id", referencedColumnName = "id")
	private OrganizationSecretMapping organizationSecretMapping;
	
	
	@OneToMany(mappedBy="organization")
    private Set<Ticket> tickets;
	
	@OneToMany(mappedBy="organization")
    private Set<Administrator> administrators;

	public Organization() {
		super();
	}

	public Organization(@NotNull @Size(min = 1, max = 50, message = "max 50 characters") String name,
			@NotNull @Size(min = 1, max = 50, message = "max 50 characters") String domain,
			@NotNull OrganizationSecretMapping organizationSecretMapping) {
		super();
		this.name = name;
		this.domain = domain;
		this.organizationSecretMapping = organizationSecretMapping;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public OrganizationSecretMapping getOrganizationSecretMapping() {
		return organizationSecretMapping;
	}

	public void setOrganizationSecretMapping(OrganizationSecretMapping organizationSecretMapping) {
		this.organizationSecretMapping = organizationSecretMapping;
	}
	
	

}
