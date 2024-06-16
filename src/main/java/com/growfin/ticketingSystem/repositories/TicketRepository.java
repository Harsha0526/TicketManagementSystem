package com.growfin.ticketingSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.growfin.ticketingSystem.models.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Query(nativeQuery = true, value = "SELECT tic.* FROM tickets tic JOIN administrators admin ON tic.admin_id = admin.id "
            + "WHERE (tic.added_by = (:customer) OR (:customer) = '') "
            + "AND (tic.status = (:status) OR (:status) = '') AND (admin.email = (:assignedTo) OR (:assignedTo) = '')")
    List<Ticket> getTicketsByQueryParams(@Param("assignedTo") String assignedTo, @Param("status") String status, @Param("customer") String customer);
}
