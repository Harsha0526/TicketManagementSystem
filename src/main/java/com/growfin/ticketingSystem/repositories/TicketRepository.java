package com.growfin.ticketingSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.growfin.ticketingSystem.models.Ticket;


@Repository("ticketRepository")
public interface TicketRepository extends JpaRepository<Ticket, String> {
	
	
	@Query(nativeQuery = true, value = "SELECT tic.* FROM tickets tic JOIN administrators admin on tic.admin_id = admin.id "
			+ "WHERE (added_by = (:customer) or (:customer) = '') "
			+ "and (status = (:status) or (:status) = '' ) and (admin.email = (:assingedTo) or (:assingedTo) = '') ")
	List<Ticket> getTicketByQueryParam(String assingedTo, String status, String customer);

}
