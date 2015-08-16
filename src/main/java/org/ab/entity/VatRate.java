package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VatRate")
public class VatRate {

	@Id
	@Column(name="vatRateIdn")
	private String vatRateIdn;

	@Column(name="vatRateDesc")
	private String vatRateDesc;

	@Column(name="value")
	private Integer value;

	private VatRate() {
	}

	public VatRate(final String vatRateIdn, final String vatRateDesc, final Integer value) {
		this.vatRateIdn = vatRateIdn;
		this.vatRateDesc = vatRateDesc;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	public String getVatRateDesc() {
		return vatRateDesc;
	}
	public String getVatRateIdn() {
		return vatRateIdn;
	}
	public void setValue(final Integer value) {
		this.value = value;
	}
	public void setVatRateDesc(final String vatRateDesc) {
		this.vatRateDesc = vatRateDesc;
	}
	public void setVatRateIdn(final String vatRateIdn) {
		this.vatRateIdn = vatRateIdn;
	}


}
