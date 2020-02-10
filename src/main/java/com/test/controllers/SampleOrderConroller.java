package com.test.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.helper.Helper.TemplateViewMode;
import com.test.models.Factory;
import com.test.models.SampleOrder;
import com.test.services.FactoryService;
import com.test.services.ItemService;
import com.test.services.ProductTypeService;
import com.test.services.SampleOrderItemService;
import com.test.services.SampleOrderService;

@Controller
@RequestMapping("/" + SampleOrderConroller.BASEURI)
public class SampleOrderConroller {
	private static final String BASE_URI = "base_uri";

	protected static final String BASEURI = "sampleorder";

	@Autowired
	private SampleOrderService sampleOrderService;
	@Autowired
	private SampleOrderItemService sampleOrderItemService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private FactoryService factoryService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemController itemController;

	@GetMapping
	public String newSampleOrderInit(ModelMap model) {
		return sampleOrderInitView(null, new SampleOrder(), model, TemplateViewMode.Create);
	}

	@GetMapping("/final")
	public String newSampleOrderFinal(ModelMap model) {
		return sampleOrderFinalView(null, new SampleOrder(), model, TemplateViewMode.Create);
	}

	@GetMapping("/{id}")
	public String updateSampleOrder(@PathVariable Integer id, ModelMap model) {
		SampleOrder sampleOrder = sampleOrderService.findSampleOrder(id);
		return sampleOrderFinalView(id, sampleOrder, model, TemplateViewMode.Update);
	}

	private String sampleOrderInitView(Integer id, SampleOrder sampleOrder, ModelMap model, TemplateViewMode viewMode) {
		Integer userId = 0000;

		model.addAttribute(BASE_URI, BASEURI);
		model.addAttribute("view_mode", viewMode);
		model.addAttribute("product_types", productTypeService.findAllProductType());
		itemController.setModelArrtibutesOfItemsTable(model, userId);
		return "sample_order_init";
	}

	private String sampleOrderFinalView(Integer id, SampleOrder sampleOrder, ModelMap model,
			TemplateViewMode viewMode) {
		Integer userId = 0000;

		if (id != null) {
			itemService.setItemsToSession(userId, sampleOrderItemService.findAllSampleOrderItemBySampleOrderId(id));
		}

		model.addAttribute("tbl_chbx", "no");
		model.addAttribute(BASE_URI, BASEURI);
		model.addAttribute("view_mode", viewMode);
		model.addAttribute("id", id);
		model.addAttribute("sampleOrder", sampleOrder);
		model.addAttribute("factories", getFactories());

		itemController.setModelArrtibutesOfItemsTable(model, userId);

		return "sample_order_final";
	}

	@PostMapping("/final")
	public String newSampleOrder(@ModelAttribute @Validated SampleOrder sampleOrder, ModelMap model) throws Exception {
		Map<String, String> response = new HashMap<String, String>();
		sampleOrderService.createSampleOrder(sampleOrder);
		response.put("status", "success");
		return "redirect:list";
	}

	@PostMapping("/final/{id}")
	public String updateSampleOrder(@PathVariable Integer id, @Validated SampleOrder sampleOrder, ModelMap model) {
		Map<String, String> response = new HashMap<String, String>();
		sampleOrderService.updateSampleOrder(id, sampleOrder);
		response.put("status", "success");
		return "redirect:list";
	}

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, String> batchDeleteSampleOrder(@RequestParam(value = "itemIds[]") List<Integer> sampleOrderIds) {
		Map<String, String> response = new HashMap<String, String>();
		boolean status = sampleOrderService.deleteSampleOrders(sampleOrderIds);
		System.out.println(status);
		response.put("status", status ? "success" : "failed");
		return response;
	}

	@GetMapping("/list")
	public String sampleOrderList(ModelMap model) {
		model.addAttribute(BASE_URI, BASEURI);
		model.addAttribute("factories", getFactories());
		model.addAttribute("sample_order_list", sampleOrderService.findAllSampleOrder());
		return "sample_orders";
	}

	private List<Factory> getFactories() {
		return factoryService.findAllFactory();
	}
}
