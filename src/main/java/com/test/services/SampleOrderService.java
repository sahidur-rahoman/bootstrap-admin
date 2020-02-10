package com.test.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.models.SampleOrder;

@Service
public interface SampleOrderService {

	public SampleOrder createSampleOrder(SampleOrder sampleOrder) throws Exception;

	public SampleOrder updateSampleOrder(Integer id, SampleOrder sampleOrder);

	public boolean deleteSampleOrders(List<Integer> sampleOrderIds);

	public Integer getSampleOrderId(String orderNumber);

	public List<String> getAllSampleOrderNumber();

	public SampleOrder findSampleOrder(Integer id);

	public List<SampleOrder> findAllSampleOrder();

}
