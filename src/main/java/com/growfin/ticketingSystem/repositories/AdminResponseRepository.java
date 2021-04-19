package com.growfin.ticketingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.growfin.ticketingSystem.models.AdminResponse;

public interface AdminResponseRepository extends JpaRepository<AdminResponse, String> {
	
	

}
