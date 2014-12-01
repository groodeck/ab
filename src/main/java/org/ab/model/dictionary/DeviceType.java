package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum DeviceType {
	MODEM("Modem"),
	TUNER("Tuner");
	
	private final String desc;

	private DeviceType(final String desc){
		this.desc = desc;
	}
	
	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final DeviceType type : values()){
			valueMap.put(type.name(), type.getDesc());
		}
		return valueMap;
	}

	public String getDesc() {
		return desc;
	}
}
