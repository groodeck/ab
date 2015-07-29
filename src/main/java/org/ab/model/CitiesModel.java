package org.ab.model;

import java.util.List;

import org.assertj.core.util.Lists;

public class CitiesModel {

	private List<CityModel> cities;

	public CitiesModel(){
		cities = Lists.newArrayList();
	}

	public List<CityModel> getCities() {
		return cities;
	}

	public void setCities(final List<CityModel> cities) {
		this.cities = cities;
	}
}
