package com.test.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.models.Store;

@Service
public interface StoreService {

	public Store createStore(Store store);

	public Store updateStore(Integer id, Store store);

	public boolean deleteStores(List<Integer> storeIds);

	public String getStoreName(Integer id);

	public List<String> getAllStoreName();

	public Store findStore(Integer id);

	public List<Store> findAllStore();

	public Map<Integer, String> getNameMapWithIdFromStoreList(List<Store> stores);
}
