package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.models.Company;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Integer> {

	@Query("SELECT cmp.name from companies cmp WHERE cmp.id = :id")
	public String getCompanyNameById(@Param("id") Integer id);
}
