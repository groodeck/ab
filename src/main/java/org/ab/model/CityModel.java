package org.ab.model;

public class CityModel {

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
