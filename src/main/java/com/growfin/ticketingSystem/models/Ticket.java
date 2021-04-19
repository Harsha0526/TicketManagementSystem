package com.growfin.ticketingSystem.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.growfin.ticketingSystem.models.payloads.DateAudit;

@Entity
@Table(name = "tickets")
public class Ticket extends DateAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull
	@Size(min = 1, max = 500, message = "Max description 500 characters")
	@Column(name = "description")
	private String description;

	@NotNull
	@Size(min = 1, max = 50, message = "Max title 50 characters")
	@Column(name = "title")
	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "closed_at")
	private Date closedAt;

	@NotNull
	@Size(min = 1, max = 50, message = "Max status 50 characters")
	@Column(name = "status")
	private String status;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_id", referencedColumnName = "id")
	private Administrator administrator;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "org_id", referencedColumnName = "id")
	private Organization organization;

	@NotNull
	@Column(name = "added_by")
	@Email
	private String addedBy;
	
	
	@OneToMany(mappedBy="ticket")
    private Set<AdminResponse> adminResponses;
	

	public Ticket(@NotNull @Size(min = 1, max = 500, message = "Max description 500 characters") String description,
			@NotNull @Size(min = 1, max = 50, message = "Max title 50 characters") String title, @NotNull String status,
			@NotNull Administrator administrator, @NotNull Organization organization, @NotNull @Email String addedBy) {
		super();
		this.description = description;
		this.title = title;
		this.status = status;
		this.administrator = administrator;
		this.organization = organization;
		this.addedBy = addedBy;
	}
	

	public Ticket(String id,
			@NotNull @Size(min = 1, max = 500, message = "Max description 500 characters") String description,
			@NotNull @Size(min = 1, max = 50, message = "Max title 50 characters") String title,
			@NotNull @Size(min = 1, max = 50, message = "Max status 50 characters") String status,
			@NotNull Administrator administrator, @NotNull Organization organization) {
		super();
		this.id = id;
		this.description = description;
		this.title = title;
		this.status = status;
		this.administrator = administrator;
		this.organization = organization;
	}


	public Ticket() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getClosedAt() {
		return closedAt;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
