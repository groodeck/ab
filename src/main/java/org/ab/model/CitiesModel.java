package org.ab.model;

import java.util.List;

import org.ab.ui.ResultPage;

public class CitiesModel extends ResultPage<CityModel> {

	@Override
	public List<CityModel> getRecords() {
		return records;
	}

	@Override
	public void setRecords(final List<CityModel> records) {
		this.records = records;
	}
}
