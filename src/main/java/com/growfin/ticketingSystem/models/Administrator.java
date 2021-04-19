package com.growfin.ticketingSystem.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.growfin.ticketingSystem.models.payloads.DateAudit;

@Entity
@Table(name = "administrators", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "email" }) })
public class Administrator extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	@Size(min = 4, max = 40, message = "Max 50 characters")
	@Column(name = "first_name")
	private String firstName;

	@Size(min = 4, max = 40, message = "Max 50 characters")
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Size(max = 40)
	@Email
	@Column(name = "email")
	private String email;

	@NotNull
	@Size(max = 100)
	@Column(name = "password")
	private String password;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;



	public Administrator(@NotNull @Size(min = 4, max = 40, message = "Max 50 characters") String firstName,
			@Size(min = 4, max = 40, message = "Max 50 characters") String lastName,
			@NotNull @Size(max = 40) @Email String email, @NotNull @Size(max = 100) String password,
			@NotNull Organization organization) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.organization = organization;
	}

	public Administrator() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	

}
