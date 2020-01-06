package com.test.my_packages.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.models.Product;

@Controller
@RequestMapping("/products")
public class ProductsController {

	/*
	 * @Autowired ProductService productService;
	 */
	@GetMapping
	public String productList(Model model) {
		List<Product> products =  new ArrayList<Product>();/* productService.findAllProducts(); */

		model.addAttribute("products", products);

		return "products";
	}

	@PostMapping("/new")
	public Product newProduct(Product product) {
		product = null;/*productService.createProduct(product);*/
		return product;
	}

	@PostMapping("/update")
	public Product updateProduct(Product product) {
		product = null; /* productService.updateProduct(product); */
		return product;
	}

	@DeleteMapping
	public boolean deleteProduct(int productId) {
		boolean flag;
		try {
			flag = true; /* productService.deleteProduct(productId); */
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
