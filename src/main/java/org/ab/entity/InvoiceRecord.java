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
@Table(name="InvoiceRecord")
public class InvoiceRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @JoinColumn(name="invoiceId", insertable=false, updatable=false, nullable=false)
	private Invoice invoice;

	public Integer getInvoiceRecordId() {
		return this.invoiceRecordId;
	}

	public void setInvoiceRecordId(final Integer invoiceRecordId) {
		this.invoiceRecordId = invoiceRecordId;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getNetPrice() {
		return this.netPrice;
	}

	public void setNetPrice(final BigDecimal netPrice) {
		this.netPrice = netPrice;
	}

	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(final BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public Integer getVatRate() {
		return this.vatRate;
	}

	public void setVatRate(final Integer vatRate) {
		this.vatRate = vatRate;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public BigDecimal getGrossAmount() {
		return this.grossAmount;
	}

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}



}
