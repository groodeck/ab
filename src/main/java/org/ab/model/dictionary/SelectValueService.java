package org.ab.model.dictionary;

import java.util.Map;

import org.ab.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class SelectValueService {

	@Autowired
	private CityService cityService;
	
	public Map<String, ?> getSubscriberDictionaries() {
		final Map<String, Map<String, String>> results = Maps.newHashMap();
		results.put("clientTypes", ClientType.asValueMap());
		results.put("cities", cityService.getCityDictionary());
		return results;
	}


}
