package com.haha.dao;

import java.util.List;

import com.haha.entity.Area;
import com.haha.entity.City;
import com.haha.entity.Province;

public interface AddressDao {
	List<Province> getProvinces();
	List<City> getCities(String id);
	List<Area> getAreas(String id);
}
