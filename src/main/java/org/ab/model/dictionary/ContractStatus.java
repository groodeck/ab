package org.ab.model.dictionary;

import java.util.Collection;
import java.util.Map;

import org.assertj.core.util.Lists;

import com.google.common.collect.Maps;

public enum ContractStatus {
	IN_PROGRESS("W realizacji"),
	DONE("Zrealizowane"),
	NOT_REALIZED("Nie zrealizowane"),
	SUSPENDED("Zawieszony"),
	RESIGNATION("Rezygnacja"),
	INACTIVE("Nieaktywny");

	public static Collection<ContractStatus> activeSubscriberStatuses() {
		return Lists.newArrayList(IN_PROGRESS, DONE,RESIGNATION);
	}

	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final ContractStatus type : values()){
			valueMap.put(type.name(), type.getDesc());
		}
		return valueMap;
	}

	private final String desc;

	private ContractStatus(final String desc){
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
