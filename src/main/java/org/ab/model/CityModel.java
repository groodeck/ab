package org.ab.model;

import org.ab.ui.SortOrder;
import org.ab.ui.SortableColumn;
import org.ab.ui.TableHeader;

public class CityModel {

	public static final TableHeader resultTableHeader = new TableHeader(
			new SortableColumn("cityIdn", "c.cityIdn", "Identyfikator", SortOrder.ASC),
			new SortableColumn("cityDescription", "c.cityDescription", "Nazwa miasta"));

	private String cityIdn;
	private String cityDesc;

	public CityModel(){
	}

	public CityModel(final String cityIdn, final String cityDesc) {
		super();
		this.cityIdn = cityIdn;
		this.cityDesc = cityDesc;
	}

	public String getCityDesc() {
		return cityDesc;
	}
	public String getCityIdn() {
		return cityIdn;
	}
	public void setCityDesc(final String cityDesc) {
		this.cityDesc = cityDesc;
	}
	public void setCityIdn(final String cityIdn) {
		this.cityIdn = cityIdn;
	}
}
