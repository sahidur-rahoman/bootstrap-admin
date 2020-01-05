package com.test.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.models.Category;
import com.test.repositories.CategoriesRepository;
import com.test.services.CategoriesService;

@Component
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired public CategoriesRepository categoriesRepository;
	
	public Category getCategory(Integer id) {
		return categoriesRepository.getOne(id);
	}
}
