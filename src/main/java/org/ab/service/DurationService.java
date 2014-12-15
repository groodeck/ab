package org.ab.service;

import java.util.List;
import java.util.Map;

import org.ab.dao.ContractDurationDao;
import org.ab.entity.ContractDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class DurationService {

	@Autowired
	private ContractDurationDao contractDurationDao;
	
	public Map<String, String> getDurationDictionary(){
		final List<ContractDuration> durations = contractDurationDao.findAll();
		final Map<String, String> results = Maps.newHashMap();
		for(final ContractDuration duration : durations){
			results.put(duration.getDurationIdn(), duration.getDurationDescription());
		}
		return results;
	}
}
