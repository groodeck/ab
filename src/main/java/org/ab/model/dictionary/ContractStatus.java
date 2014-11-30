package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum ContractStatus {
	IN_PROGRESS("W realizacji"),
	DONE("Zrealizowane"),
	NOT_REALIZED("Nie zrealizowane"),
	RESIGNATION("Rezygnacja");
	
	private final String desc;

	private ContractStatus(final String desc){
		this.desc = desc;
	}
	
	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final ContractStatus type : values()){
			valueMap.put(type.name(), type.getDesc());
		}
		return valueMap;
	}

	public String getDesc() {
		return desc;
	}
}
