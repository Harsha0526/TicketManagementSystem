package com.growfin.ticketingSystem.models;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import com.growfin.ticketingSystem.models.payloads.DateAudit;


@Entity
@Table(name = "tickets")
public class Ticket extends DateAudit {

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

    @OneToMany(mappedBy = "ticket")
    private Set<AdminResponse> adminResponses;

    /**
     * Default constructor.
     */
    public Ticket() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param description   the description of the ticket
     * @param title         the title of the ticket
     * @param status        the status of the ticket
     * @param administrator the administrator associated with the ticket
     * @param organization  the organization associated with the ticket
     * @param addedBy       the email of the person who added the ticket
     */
    public Ticket(@NotNull @Size(min = 1, max = 500, message = "Max description 500 characters") String description,
                  @NotNull @Size(min = 1, max = 50, message = "Max title 50 characters") String title,
                  @NotNull String status, @NotNull Administrator administrator,
                  @NotNull Organization organization, @NotNull @Email String addedBy) {
        super();
        this.description = description;
        this.title = title;
        this.status = status;
        this.administrator = administrator;
        this.organization = organization;
        this.addedBy = addedBy;
    }

    /**
     * Parameterized constructor with ID.
     *
     * @param id            the ID of the ticket
     * @param description   the description of the ticket
     * @param title         the title of the ticket
     * @param status        the status of the ticket
     * @param administrator the administrator associated with the ticket
     * @param organization  the organization associated with the ticket
     */
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

    /**
     * Gets the ID of the ticket.
     * @return the ID of the ticket
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the ticket.
     * @param id the new ID of the ticket
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the description of the ticket.
     * @return the description of the ticket
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the ticket.
     * @param description the new description of the ticket
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the title of the ticket.
     * @return the title of the ticket
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the ticket.
     * @param title the new title of the ticket
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the date when the ticket was closed.
     * @return the closing date of the ticket
     */
    public Date getClosedAt() {
        return closedAt;
    }

    /**
     * Sets the date when the ticket was closed.
     * @param closedAt the new closing date of the ticket
     */
    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    /**
     * Gets the status of the ticket.
     * @return the status of the ticket
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the ticket.
     * @param status the new status of the ticket
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the administrator associated with the ticket.
     * @return the administrator of the ticket
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
     * Sets the administrator associated with the ticket.
     * @param administrator the new administrator of the ticket
     */
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    /**
     * Gets the email of the person who added the ticket.
     * @return the email of the person who added the ticket
     */
    public String getAddedBy() {
        return addedBy;
    }

    /**
     * Sets the email of the person who added the ticket.
     * @param addedBy the new email of the person who added the ticket
     */
    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    /**
     * Gets the organization associated with the ticket.
     * @return the organization of the ticket
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * Sets the organization associated with the ticket.
     * @param organization the new organization of the ticket
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * Gets the set of admin responses associated with the ticket.
     * @return the set of admin responses
     */
    public Set<AdminResponse> getAdminResponses() {
        return adminResponses;
    }

    /**
     * Sets the set of admin responses associated with the ticket.
     * @param adminResponses the new set of admin responses
     */
    public void setAdminResponses(Set<AdminResponse> adminResponses) {
        this.adminResponses = adminResponses;
    }
}
