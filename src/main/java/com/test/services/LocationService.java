package com.test.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.models.Location;

@Service
public interface LocationService {

	public Location createLocation(Location location);

	public Location updateLocation(Integer id, Location location);

	public boolean deleteLocations(List<Integer> locationIds);

	public String getLocationName(Integer id);

	public List<String> getAllLocationName();

	public List<String> getAllLocationNameByStoreId(Integer storeId);

	public Location findLocation(Integer id);

	public List<Location> findAllLocation();

	public List<Location> findAllLocationByStoreId(Integer storeId);

	public Map<Integer, String> getNameMapWithIdFromLocationList(List<Location> Categories);
}
