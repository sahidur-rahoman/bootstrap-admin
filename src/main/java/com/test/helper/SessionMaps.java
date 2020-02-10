package com.test.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author sahid
 *
 */
@Component
public class SessionMaps {

	private Map<Integer, String> sessionKeyMap = new HashMap<>();
	private Map<String, Map<String, Item>> itemsMap = new HashMap<>();

	private String getSessionKeyByUserId(Integer userId) {
		if (!sessionKeyMap.containsKey(userId)) {
			String sessionKey = new Date().toInstant().toString().toLowerCase();
			sessionKeyMap.put(userId, sessionKey);
		}
		return sessionKeyMap.get(userId);
	}

	public Map<String, Item> getItemsMap(Integer userId) {
		String key = getSessionKeyByUserId(userId);

		if (!itemsMap.containsKey(key)) {
			itemsMap.put(key, new HashMap<>());
		}

		return itemsMap.get(key);
	}

	public void putItemToMap(Integer userId, Item item) throws Exception {
		Map<String, Item> itMap = getItemsMap(userId);
		String key = getItemKey(item);

		if (itMap.containsKey(key)) {
			throw new Exception("This Item already exist in the session!");
		}
		itMap.put(key, item);
	}

	public void setItemsMap(Integer userId, Map<String, Item> itemsMap) {
		String key = getSessionKeyByUserId(userId);
		this.itemsMap.replace(key, itemsMap);
	}

	public boolean removeItem(Integer userId, String key) {
		if (getItemsMap(userId).remove(key) != null) {
			return true;
		}
		return false;
	}

	public void clearItemMap(Integer userId) {
		getItemsMap(userId).clear();
	}

	public static String getItemKey(Item item) {
		return (String.valueOf(item.getProduct_id()) + String.valueOf(item.getSize_id())
				+ String.valueOf(item.getColor_id()) + String.valueOf(item.getUnit_id()));
	}
}
