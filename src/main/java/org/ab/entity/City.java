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

	private City() {
	}

	public City(final String cityIdn, final String cityDesc) {
		this.cityIdn = cityIdn;
		cityDescription = cityDesc;
	}

	public String getCityDescription() {
		return cityDescription;
	}

	public String getCityIdn() {
		return cityIdn;
	}

	public void setCityDescription(final String cityDescription) {
		this.cityDescription = cityDescription;
	}

	public void setCityIdn(final String cityIdn) {
		this.cityIdn = cityIdn;
	}
}
