package org.ab.model;

import java.util.List;

import org.assertj.core.util.Lists;

public class VatRateFormModel {

	private List<VatRateModel> rates;

	public VatRateFormModel(){
		rates = Lists.newArrayList();
	}

	public List<VatRateModel> getRates() {
		return rates;
	}

	public void setRates(final List<VatRateModel> rates) {
		this.rates = rates;
	}

}
