package com.test.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.models.Category;
import com.test.models.Product;
import com.test.services.CategoryService;
import com.test.services.ColorService;
import com.test.services.ProductService;
import com.test.services.ProductTypeService;
import com.test.services.SizeService;
import com.test.services.UnitService;

@Controller
@RequestMapping("/searchsuggest")
public class SearchSuggestController {

	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private UnitService unitService;

	@GetMapping("/categories")
	@ResponseBody
	public List<Category> getCategoriesByProductTypeId(@RequestParam Integer productTypeId) {
		return categoryService.findAllCategoryByProductTypeId(productTypeId);
	}

	@GetMapping("/products")
	@ResponseBody
	public List<Product> getProductsByCategoryId(@RequestParam Integer categoryId) {
		return productService.findAllProductByCategoryId(categoryId);
	}

	@GetMapping("/productlist")
	public String getProducts(@RequestParam Integer productTypeId, @RequestParam Integer categoryId, @RequestParam Integer productId, ModelMap model) {
		List<Product> products = new ArrayList<Product>();
		if (productId != null) {
			products.add(productService.findProduct(productId));
		} else if (categoryId != null) {
			products = getProductsByCategoryId(categoryId);
		} else {
			products = productService.findAllProductByProductTypeId(productTypeId);
		}

		model.addAttribute("product_list", products);
		model.addAttribute("product_type_names_map", productTypeService.getNameMapWithIdFromProductTypeList(productTypeService.findAllProductType()));
		model.addAttribute("category_names_map", categoryService.getNameMapWithIdFromCategoryList(categoryService.findAllCategory()));
		model.addAttribute("size_list", sizeService.findAllSize());
		model.addAttribute("color_list", colorService.findAllColor());
		model.addAttribute("unit_list", unitService.findAllUnit());

		return "fragment_tables/items_add_table";
	}
}
