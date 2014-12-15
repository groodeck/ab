package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum ClientType {
	INDIVIDUAL("Indywidualny"),
	BUSINESS("Biznesowy");
	
	private final String desc;

	private ClientType(final String desc){
		this.desc = desc;
	}
	
	public static Map<String, String> asValueMap(){
		final Map<String, String> clientMap = Maps.newHashMap();
		for(final ClientType type : values()){
			clientMap.put(type.name(), type.getDesc());
		}
		return clientMap;
	}

	public String getDesc() {
		return desc;
	}
}
