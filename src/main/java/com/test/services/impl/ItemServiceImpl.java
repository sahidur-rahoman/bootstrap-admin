package com.test.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.helper.Item;
import com.test.helper.SessionMaps;
import com.test.models.SampleOrderItem;
import com.test.services.ItemService;

@Component
public class ItemServiceImpl implements ItemService {

	@Autowired
	private SessionMaps sessionMaps;

	@Override
	public SampleOrderItem convertItemToSampleOrderItem(Item item) {
		SampleOrderItem sampleOrderItem = new SampleOrderItem();

		sampleOrderItem.setId(item.getId());
		sampleOrderItem.setCategory_id(item.getCategory_id());
		sampleOrderItem.setProduct_id(item.getProduct_id());
		sampleOrderItem.setSize_id(item.getSize_id());
		sampleOrderItem.setColor_id(item.getColor_id());
		sampleOrderItem.setUnit_id(item.getUnit_id());
		sampleOrderItem.setStyle(item.getStyle());
		sampleOrderItem.setQuantity(item.getQuantity());
		sampleOrderItem.setUnit_price(item.getUnit_price());
		sampleOrderItem.setTotal_price(item.getTotal_price());
		sampleOrderItem.setIs_deleted(item.isIs_deleted());

		return sampleOrderItem;
	}

	@Override
	public Item convertSampleOrderItemToItem(SampleOrderItem sampleOrdderItem) {
		Item item = new Item();

		item.setId(sampleOrdderItem.getId());
		item.setCategory_id(sampleOrdderItem.getCategory_id());
		item.setProduct_id(sampleOrdderItem.getProduct_id());
		item.setSize_id(sampleOrdderItem.getSize_id());
		item.setColor_id(sampleOrdderItem.getColor_id());
		item.setUnit_id(sampleOrdderItem.getUnit_id());
		item.setStyle(sampleOrdderItem.getStyle());
		item.setQuantity(sampleOrdderItem.getQuantity());
		item.setUnit_price(sampleOrdderItem.getUnit_price());
		item.setTotal_price(sampleOrdderItem.getTotal_price());
		item.setIs_deleted(sampleOrdderItem.isIs_deleted());

		return item;
	}

	@Override
	public List<SampleOrderItem> convertItemListToSampleOrderItemList(List<Item> items) {
		return items.stream().map(item -> convertItemToSampleOrderItem(item)).collect(Collectors.toList());
	}

	@Override
	public List<Item> convertSampleOrderItemListToItemList(List<SampleOrderItem> sampleOrderItems) {
		return sampleOrderItems.stream().map(sampleOrderItem -> convertSampleOrderItemToItem(sampleOrderItem))
				.collect(Collectors.toList());
	}

	@Override
	public Map<String, Item> convertItemListToItemMap(List<Item> items) {
		return items.stream().collect(Collectors.toMap(i -> SessionMaps.getItemKey(i), i -> i));
	}

	@Override
	public void setItemsToSession(Integer userId, List<SampleOrderItem> sampleOrderItems) {
		sessionMaps.setItemsMap(userId, convertItemListToItemMap(convertSampleOrderItemListToItemList(sampleOrderItems)));
	}

	@Override
	public boolean removeItemsByKey(List<String> mapKey) {
		Integer userId = 0000;
		mapKey.stream().forEach(key -> sessionMaps.removeItem(userId, key));
		return true;
	}
}
