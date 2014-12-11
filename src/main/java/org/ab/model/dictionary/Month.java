package org.ab.model.dictionary;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public enum Month {
	JAN(1, "Stycze�"),
	FEB(2, "Luty"),
	MAR(3, "Marzec"),
	APR(4, "Kwiecie�"),
	MAY(5, "Maj"),
	JUN(6, "Czerwiec"),
	JUL(7, "Lipiec"),
	AUG(8, "Sierpie�"),
	SEP(9, "Wrzesie�"),
	OCT(10, "Pa�dziernik"),
	NOV(11, "Listopad"),
	DEC(12, "Grudzie�");
	
	private final int monthNumber;
	private final String desc;

	private Month(final int monthNumber, final String desc){
		this.monthNumber = monthNumber;
		this.desc = desc;
	}
	
	public static Map<Integer, String> asValueMap(){
		final Map<Integer, String> valueMap = Maps.newTreeMap();
		for(final Month type : values()){
			valueMap.put(type.getMonthNumber(), type.getDesc());
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
