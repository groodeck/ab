package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum VatRate {
	VAT_ZW("Zwolniony"),
	VAT_0("0"),
	VAT_7("7"),
	VAT_22("22");

	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final VatRate type : values()){
			valueMap.put(type.name(), type.getDesc());
		}
		return valueMap;
	}

	private final String desc;

	private VatRate(final String desc){
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
