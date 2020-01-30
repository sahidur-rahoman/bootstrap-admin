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
import com.test.models.Location;
import com.test.models.Store;
import com.test.services.LocationService;
import com.test.services.StoreService;

@Controller
@RequestMapping("/" + LocationConroller.BASEURI)
public class LocationConroller {
	protected static final String BASEURI = "location";

	@Autowired
	private LocationService locationService;
	@Autowired
	private StoreService storeService;

	@GetMapping
	public String newLocation(ModelMap model) {
		return locationView(null, new Location(), model, TemplateViewMode.New);
	}

	@GetMapping("/{id}")
	public String updateLocation(@PathVariable Integer id, ModelMap model) {
		Location location = locationService.findLocation(id);
		return locationView(id, location, model, TemplateViewMode.Update);
	}

	private String locationView(Integer id, Location location, ModelMap model, TemplateViewMode viewMode) {
		model.addAttribute("base_uri", BASEURI);
		model.addAttribute("view_mode", viewMode);
		model.addAttribute("id", id);
		model.addAttribute("stores", getStores());
		model.addAttribute("location", location);
		return "location";
	}

	@PostMapping
	public String newLocation(@Validated Location location, ModelMap model) {
		Map<String, String> response = new HashMap<String, String>();
		locationService.createLocation(location);
		response.put("status", "success");
		return "redirect:list";
	}

	@PostMapping("/{id}")
	public String updateLocation(@PathVariable Integer id, @Validated Location location, ModelMap model) {
		Map<String, String> response = new HashMap<String, String>();
		locationService.updateLocation(id, location);
		response.put("status", "success");
		return "redirect:list";
	}

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, String> batchDeleteLocation(@RequestParam(value = "itemIds[]") List<Integer> locationIds) {
		Map<String, String> response = new HashMap<String, String>();
		boolean status = locationService.deleteLocations(locationIds);
		System.out.println(status);
		response.put("status", status ? "success" : "failed");
		return response;
	}

	@GetMapping("/list")
	public String locationList(ModelMap model) {
		model.addAttribute("base_uri", BASEURI);
		model.addAttribute("stores", getStores());
		model.addAttribute("location_list", locationService.findAllLocation());
		model.addAttribute("store_map", storeService.getNameMapWithIdFromStoreList(getStores()));
		return "locations";
	}

	private List<Store> getStores() {
		return storeService.findAllStore();
	}
}
