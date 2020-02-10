package com.test.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.helper.SessionMaps;
import com.test.models.SampleOrder;
import com.test.models.SampleOrderItem;
import com.test.repositories.SampleOrderRepository;
import com.test.services.ItemService;
import com.test.services.SampleOrderItemService;
import com.test.services.SampleOrderService;

@Component
public class SampleOrderServiceImpl implements SampleOrderService {

	@Autowired
	private SessionMaps sessionMaps;
	@Autowired
	private SampleOrderRepository sampleOrderRepository;
	@Autowired
	private SampleOrderItemService sampleOrderItemService;
	@Autowired
	private ItemService itemService;

	@Override
	public SampleOrder createSampleOrder(SampleOrder sampleOrder) throws Exception {
		
		if(getSampleOrderId(sampleOrder.getOrder_no()) != null) {
			throw new Exception("Sample order table already have this order number");
		}
		
		Integer userId = 0000;
		Integer instituteId = 0000;
		Integer companyId = 0000;

		sampleOrder.setInstitute_id(instituteId);
		sampleOrder.setCompany_id(companyId);
		sampleOrder.setOrder_status(SampleOrder.OrderStatus.pending);
		sampleOrder.setStock_status(SampleOrder.StockStatus.pending);
		sampleOrder.setDelivery_status(SampleOrder.DeliveryStatus.due);
		sampleOrder.setProduction_status(SampleOrder.ProductionStatus.no);

		sampleOrder.setCreated_by(userId);
		sampleOrder.setCreated_date(getCurrentDate());
		sampleOrder.setIs_deleted(false);

		sampleOrderRepository.save(sampleOrder);

		sessionMaps.getItemsMap(userId).values().stream().collect(Collectors.toList()).forEach(item -> {
			SampleOrderItem sampleOrderItem = itemService.convertItemToSampleOrderItem(item);

			sampleOrderItem.setSample_order_id(getSampleOrderId(sampleOrder.getOrder_no()));
			sampleOrderItem.setIs_deleted(false);

			sampleOrderItemService.createSampleOrderItem(sampleOrderItem);
		});

		return sampleOrder;
	}

	@Override
	public SampleOrder updateSampleOrder(Integer id, SampleOrder sampleOrder) {
		Integer userId = 0000;
		SampleOrder smOrder = findSampleOrder(id);

		smOrder.setInstitute_id(sampleOrder.getInstitute_id());
		smOrder.setCompany_id(sampleOrder.getCompany_id());
		smOrder.setBuyer_id(sampleOrder.getBuyer_id());
		smOrder.setOrder_no(sampleOrder.getOrder_no());
		smOrder.setFactory_id(sampleOrder.getFactory_id());
		smOrder.setPo_no(sampleOrder.getPo_no());
		smOrder.setIssue_date(sampleOrder.getIssue_date());
		smOrder.setOrder_date(sampleOrder.getOrder_date());
		smOrder.setDelivery_date(sampleOrder.getDelivery_date());
		smOrder.setReference(sampleOrder.getReference());
		smOrder.setTerms(sampleOrder.getTerms());
		smOrder.setMerchandiser(sampleOrder.getMerchandiser());
		smOrder.setReceive_person_id(sampleOrder.getReceive_person_id());
		smOrder.setQuantity(sampleOrder.getQuantity());
		smOrder.setTotal_amount(sampleOrder.getTotal_amount());
		smOrder.setSample_design(sampleOrder.getSample_design());
		/*
		 * smOrder.setOrder_status(sampleOrder.getOrder_status());
		 * smOrder.setStock_status(sampleOrder.getStock_status());
		 * smOrder.setDelivery_status(sampleOrder.getDelivery_status());
		 * smOrder.setProduction_status(sampleOrder.getProduction_status());
		 */

		smOrder.setModified_by(0000);
		smOrder.setModified_date(getCurrentDate());
		smOrder.setIs_deleted(false);

		sampleOrderRepository.save(smOrder);

		sampleOrderItemService.findAllSampleOrderItemBySampleOrderId(smOrder.getId()).stream().forEach(smit -> {
			smit.setIs_deleted(true);
			sampleOrderItemService.updateSampleOrderItem(smit.getId(), smit);
		});


		sessionMaps.getItemsMap(userId).values().stream().collect(Collectors.toList()).forEach(item -> {
			SampleOrderItem sampleOrderItem = itemService.convertItemToSampleOrderItem(item);

			sampleOrderItem.setSample_order_id(smOrder.getId());
			sampleOrderItem.setIs_deleted(false);

			sampleOrderItemService.createSampleOrderItem(sampleOrderItem);
		});
		return smOrder;
	}

	@Override
	public boolean deleteSampleOrders(List<Integer> sampleOrderIds) {
		try {
			sampleOrderIds.stream().forEach(id -> {
				SampleOrder sampleOrder = findSampleOrder(id);

				sampleOrder.setModified_by(0000);
				sampleOrder.setModified_date(getCurrentDate());
				sampleOrder.setIs_deleted(true);

				sampleOrderRepository.save(sampleOrder);
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Integer getSampleOrderId(String orderNumber) {
		return sampleOrderRepository.getSampleOrderId(orderNumber);
	}

	@Override
	public List<String> getAllSampleOrderNumber() {
		return sampleOrderRepository.getAllSampleOrderNumber();
	}

	@Override
	public SampleOrder findSampleOrder(Integer id) {
		Optional<SampleOrder> sampleOrder = sampleOrderRepository.findById(id);

		return sampleOrder.isPresent() ? sampleOrder.get() : null;
	}

	@Override
	public List<SampleOrder> findAllSampleOrder() {
		return sampleOrderRepository.findAll().stream().filter(sampleOrder -> !sampleOrder.isIs_deleted())
				.collect(Collectors.toList());
	}

	private Date getCurrentDate() {
		return new Date();
	}

}
