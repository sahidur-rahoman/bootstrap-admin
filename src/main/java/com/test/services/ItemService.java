package com.test.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.helper.Item;
import com.test.models.SampleOrderItem;

@Service
public interface ItemService {

	public SampleOrderItem convertItemToSampleOrderItem(Item item);

	public Item convertSampleOrderItemToItem(SampleOrderItem sampleOrdderItem);

	public List<SampleOrderItem> convertItemListToSampleOrderItemList(List<Item> items);

	public List<Item> convertSampleOrderItemListToItemList(List<SampleOrderItem> sampleOrderItems);

	public Map<String, Item> convertItemListToItemMap(List<Item> items);

	public void setItemsToSession(Integer userId, List<SampleOrderItem> sampleOrderItems);

	public boolean removeItemsByKey(List<String> mapKey);
}
