package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum VatRate {
	VAT_ZW("Zwolniony", 0),
	VAT_0("0", 0),
	VAT_7("7", 7),
	VAT_22("22", 22);

	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final VatRate type : values()){
			valueMap.put(type.name(), type.getDesc());
		}
		return valueMap;
	}

	private final String desc;
	private int rate;

	private VatRate(final String desc, final int rate){
		this.rate = rate;
		this.desc = desc;
	}

	public String getDesc() {
		return this.desc;
	}

	public int getRate() {
		return this.rate;
	}
}
