package com.test.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.helper.Helper.TemplateViewMode;
import com.test.models.Store;
import com.test.services.StoreService;

@Controller
@RequestMapping("/" + StoreConroller.BASEURI)
public class StoreConroller {
	protected static final String BASEURI = "store";

	@Autowired
	private StoreService storeService;

	@GetMapping
	public String newStore(ModelMap model) {
		return storeView(null, new Store(), model, TemplateViewMode.New);
	}

	@GetMapping("/{id}")
	public String updateStore(@PathVariable Integer id, ModelMap model) {
		Store store = storeService.findStore(id);
		return storeView(id, store, model, TemplateViewMode.Update);
	}

	private String storeView(Integer id, Store store, ModelMap model, TemplateViewMode viewMode) {
		model.addAttribute("base_uri", BASEURI);
		model.addAttribute("view_mode", viewMode);
		model.addAttribute("id", id);
		model.addAttribute("store", store);
		return "store";
	}

	@PostMapping
	public String newStore(@Validated Store store, ModelMap model) {
		Map<String, String> response = new HashMap<String, String>();
		storeService.createStore(store);
		response.put("status", "success");
		return "redirect:list";
	}

	@PostMapping("/{id}")
	public String updateStore(@PathVariable Integer id, @Validated Store store, ModelMap model) {
		Map<String, String> response = new HashMap<String, String>();
		storeService.updateStore(id, store);
		response.put("status", "success");
		return "redirect:list";
	}

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, String> batchDeleteStore(@RequestParam(value = "itemIds[]") List<Integer> storeIds) {
		Map<String, String> response = new HashMap<String, String>();
		boolean status = storeService.deleteStores(storeIds);
		System.out.println(status);
		response.put("status", status ? "success" : "failed");
		return response;
	}

	@GetMapping("/list")
	public String storeList(ModelMap model) {
		model.addAttribute("base_uri", BASEURI);
		//TODO: Have to add attribute of buyers;
		model.addAttribute("store_list", storeService.findAllStore());
		return "stores";
	}
}
