package com.test.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.models.Categories;
import com.test.repositories.CategoriesRepository;
import com.test.services.CategoriesService;

@Component
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired public CategoriesRepository categoriesRepository;
	
	public Categories getCategory(Integer id) {
		return categoriesRepository.getOne(id);
	}
}
