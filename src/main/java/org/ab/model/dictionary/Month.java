package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum Month {
	JAN(1, "Styczeñ"),
	FEB(2, "Luty"),
	MAR(3, "Marzec"),
	APR(4, "Kwiecieñ"),
	MAY(5, "Maj"),
	JUN(6, "Czerwiec"),
	JUL(7, "Lipiec"),
	AUG(8, "Sierpieñ"),
	SEP(9, "Wrzesieñ"),
	OCT(10, "PaŸdziernik"),
	NOV(11, "Listopad"),
	DEC(12, "Grudzieñ");
	
	private final int monthNumber;
	private final String desc;

	private Month(final int monthNumber, final String desc){
		this.monthNumber = monthNumber;
		this.desc = desc;
	}
	
	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final Month type : values()){
			valueMap.put(String.valueOf(type.getMonthNumber()), type.getDesc());
		}
		return valueMap;
	}

	public String getDesc() {
		return desc;
	}

	public int getMonthNumber() {
		return monthNumber;
	}
}
