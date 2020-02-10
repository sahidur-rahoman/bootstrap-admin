package com.test.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.helper.Item;
import com.test.helper.SessionMaps;
import com.test.models.Product;
import com.test.services.CategoryService;
import com.test.services.ColorService;
import com.test.services.ItemService;
import com.test.services.ProductService;
import com.test.services.ProductTypeService;
import com.test.services.SizeService;
import com.test.services.UnitService;

@Controller
@RequestMapping("/item")
public class ItemController {

	private static final String BASE_URI = "base_uri";

	@Autowired
	private SessionMaps sessionMaps;
	@Autowired
	private ItemService itemService;
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

	@GetMapping("/list")
	public String getProducts(@RequestParam String baseUri, @RequestParam Integer productTypeId,
			@RequestParam Integer categoryId, @RequestParam Integer productId, ModelMap model) {
		List<Product> products = new ArrayList<Product>();
		if (productId != null) {
			products.add(productService.findProduct(productId));
		} else if (categoryId != null) {
			products = productService.findAllProductByCategoryId(categoryId);
		} else {
			products = productService.findAllProductByProductTypeId(productTypeId);
		}

		model.addAttribute(BASE_URI, baseUri);
		model.addAttribute("product_list", products);
		model.addAttribute("product_type_names_map",
				productTypeService.getNameMapWithIdFromProductTypeList(productTypeService.findAllProductType()));
		model.addAttribute("category_names_map",
				categoryService.getNameMapWithIdFromCategoryList(categoryService.findAllCategory()));
		model.addAttribute("size_list", sizeService.findAllSize());
		model.addAttribute("color_list", colorService.findAllColor());
		model.addAttribute("unit_list", unitService.findAllUnit());

		return "fragment_tables/items_add_table";
	}

	@PostMapping("/add")
	public String addItem(@RequestParam String baseUri, @ModelAttribute @Validated Item item, ModelMap model) throws Exception {
		Integer userId = 0000;

		Map<String, String> response = new HashMap<String, String>();
		response.put("status", "success");
		sessionMaps.putItemToMap(userId, item);

		setModelArrtibutesOfItemsTable(model, userId);
		model.addAttribute(BASE_URI, baseUri);

		return "fragment_tables/items_table";
	}

	@PostMapping("/remove")
	@ResponseBody
	public Map<String, String> batchRemoveSampleOrder(@RequestParam(value = "itemIds[]") List<String> mapKey) {
		Map<String, String> response = new HashMap<String, String>();
		boolean status = itemService.removeItemsByKey(mapKey);
		System.out.println(status);
		response.put("status", status ? "success" : "failed");
		return response;
	}

	public ModelMap setModelArrtibutesOfItemsTable(ModelMap model, Integer userId) {
		Map<String, Item> itemsMap = sessionMaps.getItemsMap(userId);
		Double totalQuantity = itemsMap.values().stream().collect(Collectors.summingDouble(Item::getQuantity));
		Double totalPrice = itemsMap.values().stream().collect(Collectors.summingDouble(Item::getTotal_price));

		model.addAttribute("item_map", itemsMap);
		model.addAttribute("category_names_map",
				categoryService.getNameMapWithIdFromCategoryList(categoryService.findAllCategory()));
		model.addAttribute("product_names_map",
				productService.getNameMapWithIdFromProductList(productService.findAllProduct()));
		model.addAttribute("size_names_map", sizeService.getNameMapWithIdFromSizeList(sizeService.findAllSize()));
		model.addAttribute("color_names_map", colorService.getNameMapWithIdFromColorList(colorService.findAllColor()));
		model.addAttribute("unit_names_map", unitService.getNameMapWithIdFromUnitList(unitService.findAllUnit()));
		model.addAttribute("total_quantity", totalQuantity);
		model.addAttribute("total_price", totalPrice);

		return model;
	}
}
