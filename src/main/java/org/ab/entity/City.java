package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="City")
public class City {

	@Id
	@Column(name="city_idn")
	private String cityIdn;

	@Column(name="city_description")
	private String cityDescription;

	public String getCityIdn() {
		return cityIdn;
	}

	public String getCityDescription() {
		return cityDescription;
	}

	private void setCityIdn(String cityIdn) {
		this.cityIdn = cityIdn;
	}

	private void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}
}
