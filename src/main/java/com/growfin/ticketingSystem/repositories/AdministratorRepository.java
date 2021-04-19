package com.growfin.ticketingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.growfin.ticketingSystem.models.Administrator;

@Repository("administratorRepository")
public interface AdministratorRepository extends JpaRepository<Administrator, String> {

	Administrator findByEmail(String email);

	Boolean existsByEmail(String email);

	@Query(nativeQuery = true, value = "SELECT admin_id FROM tickets tic WHERE tic.org_id = (:orgId) and tic.status = (:status) group by tic.admin_id order by count(*) ASC limit 1")
	public String findAvailableAdmin(@Param("orgId") String orgId, @Param("status") String status);

	@Query(nativeQuery = true, value = "SELECT * FROM administrators admin WHERE admin.org_id = (:orgId) limit 1")
	public Administrator findFirstAdminstrator(@Param("orgId") String orgId);

}
