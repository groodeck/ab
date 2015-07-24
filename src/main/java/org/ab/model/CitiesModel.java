package org.ab.model;

import java.util.List;

import org.assertj.core.util.Lists;

public class CitiesModel {

	private String newCityIdn;
	private String newCityDesc;

	private List<CityModel> cities;

	public CitiesModel(){
		cities = Lists.newArrayList();
	}

	public List<CityModel> getCities() {
		return cities;
	}

	public String getNewCityDesc() {
		return newCityDesc;
	}

	public String getNewCityIdn() {
		return newCityIdn;
	}

	public void setCities(final List<CityModel> cities) {
		this.cities = cities;
	}

	public void setNewCityDesc(final String newCityDesc) {
		this.newCityDesc = newCityDesc;
	}

	public void setNewCityIdn(final String newCityIdn) {
		this.newCityIdn = newCityIdn;
	}

}
