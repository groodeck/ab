package org.ab.service;

import java.util.List;
import java.util.Map;

import org.ab.dao.CityDao;
import org.ab.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class CityService {

	@Autowired
	private CityDao cityDao;
	
	public Map<String, String> getCityDictionary(){
		final List<City> cities = cityDao.findAll();
		final Map<String, String> results = Maps.newHashMap();
		for(final City city : cities){
			results.put(city.getCityIdn(), city.getCityDescription());
		}
		return results;
	}
}
