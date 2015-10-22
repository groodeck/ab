package org.ab.model.dictionary;

import java.util.Map;

import com.google.common.collect.Maps;

public enum ReportType {
	ALL_SUBSCRIBERS("Raport abonentów", "SubscribersReport.xlsx", "Abonenci.%s.xlsx");

	public static Map<String, String> asValueMap(){
		final Map<String, String> valueMap = Maps.newHashMap();
		for(final ReportType type : values()){
			valueMap.put(type.name(), type.getDesc());
		}
		return valueMap;
	}

	private final String desc;
	private final String templateFileName;
	private final String outputFilenameTemplate;

	private ReportType(final String desc, final String templateFileName, final String outputFilenameTemplate){
		this.desc = desc;
		this.templateFileName = templateFileName;
		this.outputFilenameTemplate = outputFilenameTemplate;
	}

	public String getDesc() {
		return desc;
	}

	public String getOutputFilenameTemplate() {
		return outputFilenameTemplate;
	}

	public String getTemplateFileName() {
		return templateFileName;
	}
}
