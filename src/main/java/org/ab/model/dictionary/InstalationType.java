package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum InstalationType {
	WIRELESS("Radiowy"),
	CABLE("Kablowy");

	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final InstalationType type : values()){
			valueMap.put(type.name(), type.getDesc());
		}
		return valueMap;
	}

	private final String desc;

	private InstalationType(final String desc){
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
