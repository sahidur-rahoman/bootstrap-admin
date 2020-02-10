package com.test.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.models.Product;

@Service
public interface ProductService {

	public Product createProduct(Product product);

	public Product updateProduct(Integer id, Product product);

	public boolean deleteProducts(List<Integer> productIds);

	public String getProductName(Integer id);

	public List<String> getAllProductName();

	public Product findProduct(Integer id);

	public List<Product> findAllProductByProductTypeId(Integer productTypeId);

	public List<Product> findAllProductByCategoryId(Integer categoryId);

	public List<Product> findAllProduct();

	public Map<Integer, String> getNameMapWithIdFromProductList(List<Product> findAllProduct);
}
