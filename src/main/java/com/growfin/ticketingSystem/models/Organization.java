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

/**Entity class representing an Organization.*/
@Entity
@Table(name = "organizations")
public class Organization extends DateAudit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

<<<<<<< HEAD
    @NotNull
    @Size(min = 1, max = 50, message = "Maximum 50 characters allowed")
    @Column(name = "name")
    private String name;
=======
	@NotNull
	@Size(min = 1, max = 50, message = "max 50 characters")
	@Column(name = "name")
	private String name;
	
>>>>>>> c034f24e72d6bb9c72caaafa753dba3275237bfd

    @NotNull
    @Size(min = 1, max = 50, message = "Maximum 50 characters allowed")
    @Column(name = "domain")
    private String domain;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "secret_mapping_id", referencedColumnName = "id")
    private OrganizationSecretMapping organizationSecretMapping;

    @OneToMany(mappedBy = "organization")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "organization")
    private Set<Administrator> administrators;

<<<<<<< HEAD
    /**Default constructor.*/
    public Organization() {
        super();
    }

    /*Parameterized constructor.*/
    public Organization(@NotNull @Size(min = 1, max = 50, message = "Maximum 50 characters allowed") String name,
                        @NotNull @Size(min = 1, max = 50, message = "Maximum 50 characters allowed") String domain,
                        @NotNull OrganizationSecretMapping organizationSecretMapping) {
        super();
        this.name = name;
        this.domain = domain;
        this.organizationSecretMapping = organizationSecretMapping;
    }
=======
		@Size(max = 100)
		@Column(name = "address")
		private String address;
	
		@Size(max = 15)
		@Column(name = "phone_number")
		private String phoneNumber;
	
		@NotNull
		@Column(name = "status")
		private String status;

	public Organization() {
		super();
	}

    public Organization(@NotNull @Size(min = 1, max = 50, message = "max 50 characters") String name,
                        @NotNull @Size(min = 1, max = 50, message = "max 50 characters") String domain,
                        @NotNull OrganizationSecretMapping organizationSecretMapping,
                        @Size(max = 100) String address,
                        @Size(max = 15) String phoneNumber,
                        @NotNull String status) {
		super();
		this.name = name;
		this.domain = domain;
		this.organizationSecretMapping = organizationSecretMapping;
		this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
	}
>>>>>>> c034f24e72d6bb9c72caaafa753dba3275237bfd

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

<<<<<<< HEAD

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


    public Set<Ticket> getTickets() {
        return tickets;
    }


    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Administrator> getAdministrators() {
        return administrators;
    }


    public void setAdministrators(Set<Administrator> administrators) {
        this.administrators = administrators;
=======
	public void setOrganizationSecretMapping(OrganizationSecretMapping organizationSecretMapping) {
		this.organizationSecretMapping = organizationSecretMapping;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
>>>>>>> c034f24e72d6bb9c72caaafa753dba3275237bfd
    }
}
	
