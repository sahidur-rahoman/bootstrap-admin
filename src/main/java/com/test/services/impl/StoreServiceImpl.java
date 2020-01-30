package com.test.services.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.models.Store;
import com.test.repositories.StoreRepository;
import com.test.services.StoreService;

@Component
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	public Store createStore(Store store) {
		store.setCreated_by(0000);
		store.setCreated_date(getCurrentDate());
		store.setIs_deleted(false);

		return storeRepository.save(store);
	}

	public Store updateStore(Integer id, Store store) {
		Store str = findStore(id);

		str.setName(store.getName());
		str.setCode(store.getCode());
		str.setPhone(store.getPhone());
		str.setCapacity(store.getCapacity());
		str.setAddress(store.getAddress());
		str.setDescription(store.getDescription());
		str.setStore_keeper_id(store.getStore_keeper_id());

		str.setModified_by(0000);
		str.setModified_date(getCurrentDate());
		str.setIs_deleted(false);

		return storeRepository.save(str);
	}

	public boolean deleteStores(List<Integer> storeIds) {
		try {
			storeIds.stream().forEach(id -> {
				Store store = findStore(id);

				store.setModified_by(0000);
				store.setModified_date(getCurrentDate());
				store.setIs_deleted(true);

				storeRepository.save(store);
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public String getStoreName(Integer id) {
		return storeRepository.getStoreName(id);
	}

	public List<String> getAllStoreName() {
		return storeRepository.getAllStoreName();
	}

	public Store findStore(Integer id) {
		Optional<Store> store = storeRepository.findById(id);

		return store.isPresent() ? store.get() : null;
	}

	public List<Store> findAllStore() {
		return storeRepository.findAll().stream().filter(store -> !store.isIs_deleted())
				.sorted(Comparator.comparing(Store::getName)).collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getNameMapWithIdFromStoreList(List<Store> stores) {
		return stores.stream().collect(Collectors.toMap(Store::getId, Store::getName));
	}

	private Date getCurrentDate() {
		return new Date();
	}
}
