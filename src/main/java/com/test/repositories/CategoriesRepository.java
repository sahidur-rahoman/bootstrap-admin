package com.test.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.models.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT cat.name from categories cat WHERE cat.id = :id")
	public String getCategoryNameById(@Param("id") Integer id);
}
