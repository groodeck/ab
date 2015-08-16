package org.ab.model;

public class VatRateModel {

	private String rateIdn;
	private String rateDesc;
	private Integer value;

	public VatRateModel(){
	}

	public VatRateModel(final String rateIdn, final String rateDesc,final int value) {
		super();
		this.rateIdn = rateIdn;
		this.rateDesc = rateDesc;
		this.value = value;
	}

	public String getRateDesc() {
		return rateDesc;
	}

	public String getRateIdn() {
		return rateIdn;
	}

	public Integer getValue() {
		return value;
	}

	public void setRateDesc(final String rateDesc) {
		this.rateDesc = rateDesc;
	}

	public void setRateIdn(final String rateIdn) {
		this.rateIdn = rateIdn;
	}

	public void setValue(final Integer value) {
		this.value = value;
	}

}
