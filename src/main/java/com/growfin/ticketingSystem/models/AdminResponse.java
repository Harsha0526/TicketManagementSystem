package com.growfin.ticketingSystem.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.growfin.ticketingSystem.models.payloads.DateAudit;

@Entity
@Table(name = "adminResponses")
public class AdminResponse extends DateAudit {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticket_id", referencedColumnName = "id")
	private Ticket ticket;
	
	@NotNull
	@Size(min = 1, max = 50, message = "max 500 characters")
	@Column(name = "message")
	private String message;

	public AdminResponse(@NotNull Ticket ticket,
			@NotNull @Size(min = 1, max = 50, message = "max 500 characters") String message) {
		super();
		this.ticket = ticket;
		this.message = message;
	}

}
