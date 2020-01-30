package com.test.services.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.models.Location;
import com.test.repositories.LocationRepository;
import com.test.services.LocationService;

@Component
public class LocationServiceImpl implements LocationService {

	@Autowired
	public LocationRepository locationRepository;

	@Override
	public Location createLocation(Location location) {
		location.setCreated_by(0000);
		location.setCreated_date(getCurrentDate());
		location.setIs_deleted(false);

		return locationRepository.save(location);
	}

	@Override
	public Location updateLocation(Integer id, Location location) {
		Location loc = findLocation(id);

		loc.setStore_id(location.getStore_id());
		loc.setName(location.getName());
		loc.setCode(location.getCode());
		loc.setCapacity(location.getCapacity());
		loc.setDescription(location.getDescription());

		loc.setModified_by(0000);
		loc.setModified_date(getCurrentDate());
		loc.setIs_deleted(false);

		return locationRepository.save(loc);
	}

	@Override
	public boolean deleteLocations(List<Integer> locationIds) {
		try {
			locationIds.stream().forEach(id -> {
				Location location = findLocation(id);

				location.setModified_by(0000);
				location.setModified_date(getCurrentDate());
				location.setIs_deleted(true);

				locationRepository.save(location);
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public String getLocationName(Integer id) {
		return locationRepository.getLocationName(id);
	}

	@Override
	public List<String> getAllLocationName() {
		return locationRepository.getLocationNames();
	}

	@Override
	public List<String> getAllLocationNameByStoreId(Integer storeId) {
		return locationRepository.getLocationNamesByStoreId(storeId);
	}

	@Override
	public Location findLocation(Integer id) {
		Optional<Location> location = locationRepository.findById(id);
		return location.isPresent() ? location.get() : null;
	}

	@Override
	public List<Location> findAllLocation() {
		return locationRepository.findAll().stream().filter(location -> !location.isIs_deleted())
				.sorted(Comparator.comparing(Location::getName)).collect(Collectors.toList());
	}

	@Override
	public List<Location> findAllLocationByStoreId(Integer storeId) {
		return locationRepository.getAllLocationByStoreId(storeId).stream()
				.sorted(Comparator.comparing(Location::getName)).collect(Collectors.toList());
	}

	@Override
	public Map<Integer, String> getNameMapWithIdFromLocationList(List<Location> Categories) {
		return Categories.stream().collect(Collectors.toMap(Location::getId, Location::getName));
	}

	private Date getCurrentDate() {
		return new Date();
	}

}
