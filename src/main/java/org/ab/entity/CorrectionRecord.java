package org.ab.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CorrectionRecord")
public class CorrectionRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="correctionRecordId")
	private Integer correctionRecordId;

	@Column(name="invoiceRecordId")
	private Integer invoiceRecordId;

	@Column(name="serviceName")
	private String serviceName;

	@Column(name="quantity")
	private Integer quantity;

	@Column(name="netPrice")
	private BigDecimal netPrice;

	@Column(name="netAmount")
	private BigDecimal netAmount;

	@Column(name="vatRate")
	private Integer vatRate;

	@Column(name="vatAmount")
	private BigDecimal vatAmount;

	@Column(name="grossAmount")
	private BigDecimal grossAmount;

	@ManyToOne
	@JoinColumn(name="correctionId", insertable=false, updatable=false, nullable=false)
	private Correction correction;

	public Correction getCorrection() {
		return correction;
	}

	public Integer getCorrectionRecordId() {
		return correctionRecordId;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public Integer getInvoiceRecordId() {
		return invoiceRecordId;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getServiceName() {
		return serviceName;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public Integer getVatRate() {
		return vatRate;
	}

	public void setCorrection(final Correction correction) {
		this.correction = correction;
	}

	public void setCorrectionRecordId(final Integer correctionRecordId) {
		this.correctionRecordId = correctionRecordId;
	}

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public void setInvoiceRecordId(final Integer invoiceRecordId) {
		this.invoiceRecordId = invoiceRecordId;
	}

	public void setNetAmount(final BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public void setNetPrice(final BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public void setVatRate(final Integer vatRate) {
		this.vatRate = vatRate;
	}



}
