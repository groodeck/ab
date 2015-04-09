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

	@Column(name="quantityDiff")
	private Integer quantityDiff;

	@Column(name="netPrice")
	private BigDecimal netPrice;

	@Column(name="netPriceDiff")
	private BigDecimal netPriceDiff;

	@Column(name="netAmount")
	private BigDecimal netAmount;

	@Column(name="netAmountDiff")
	private BigDecimal netAmountDiff;

	@Column(name="vatRate")
	private Integer vatRate;

	@Column(name="vatAmount")
	private BigDecimal vatAmount;

	@Column(name="vatAmountDiff")
	private BigDecimal vatAmountDiff;

	@Column(name="grossAmount")
	private BigDecimal grossAmount;

	@Column(name="grossAmountDiff")
	private BigDecimal grossAmountDiff;

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

	public BigDecimal getGrossAmountDiff() {
		return grossAmountDiff;
	}

	public Integer getInvoiceRecordId() {
		return invoiceRecordId;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public BigDecimal getNetAmountDiff() {
		return netAmountDiff;
	}

	public BigDecimal getNetPrice() {
		return netPrice;
	}

	public BigDecimal getNetPriceDiff() {
		return netPriceDiff;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getQuantityDiff() {
		return quantityDiff;
	}

	public String getServiceName() {
		return serviceName;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public BigDecimal getVatAmountDiff() {
		return vatAmountDiff;
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

	public void setGrossAmountDiff(final BigDecimal grossAmountDiff) {
		this.grossAmountDiff = grossAmountDiff;
	}

	public void setInvoiceRecordId(final Integer invoiceRecordId) {
		this.invoiceRecordId = invoiceRecordId;
	}

	public void setNetAmount(final BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public void setNetAmountDiff(final BigDecimal netAmountDiff) {
		this.netAmountDiff = netAmountDiff;
	}

	public void setNetPrice(final BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public void setNetPriceDiff(final BigDecimal netPriceDiff) {
		this.netPriceDiff = netPriceDiff;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	public void setQuantityDiff(final Integer quantityDiff) {
		this.quantityDiff = quantityDiff;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public void setVatAmountDiff(final BigDecimal vatAmountDiff) {
		this.vatAmountDiff = vatAmountDiff;
	}

	public void setVatRate(final Integer vatRate) {
		this.vatRate = vatRate;
	}



}
