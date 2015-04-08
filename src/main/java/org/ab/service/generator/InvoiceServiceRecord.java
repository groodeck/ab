package org.ab.service.generator;

import java.math.BigDecimal;

public class InvoiceServiceRecord {
	public static class Builder {
		private Integer invoiceRecordId;
		private Integer lp;
		private String serviceName;
		private Integer vatRate;
		private BigDecimal netPrice;
		private BigDecimal netAmount;
		private BigDecimal vatAmount;
		private BigDecimal grossAmount;
		private Integer quantity;

		public InvoiceServiceRecord build(){
			return new InvoiceServiceRecord(this);
		}

		public Builder withGrossAmount(final BigDecimal grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withInvoiceRecordId(final Integer invoiceRecordId) {
			this.invoiceRecordId = invoiceRecordId;
			return this;
		}

		public Builder withLp(final Integer lp) {
			this.lp = lp;
			return this;
		}

		public Builder withNetAmount(final BigDecimal netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withNetPrice(final BigDecimal netPrice) {
			this.netPrice = netPrice;
			return this;
		}

		public Builder withQuantity(final Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder withServiceName(final String serviceName) {
			this.serviceName = serviceName;
			return this;
		}

		public Builder withVatAmount(final BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}

		public Builder withVatRate(final Integer vatRate) {
			this.vatRate = vatRate;
			return this;
		}
	}

	private Integer invoiceRecordId;
	private Integer lp;
	private String serviceName;
	private Integer vatRate;
	private BigDecimal netAmount;
	private BigDecimal netPrice;
	private BigDecimal vatAmount;
	private BigDecimal grossAmount;
	private Integer quantity;

	public InvoiceServiceRecord(){
	}

	public InvoiceServiceRecord(final Builder builder) {
		invoiceRecordId = builder.invoiceRecordId;
		lp = builder.lp;
		serviceName = builder.serviceName;
		netPrice = builder.netPrice;
		vatRate = builder.vatRate;
		netAmount = builder.netAmount;
		vatAmount = builder.vatAmount;
		grossAmount = builder.grossAmount;
		quantity = builder.quantity;
	}
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}
	public Integer getInvoiceRecordId() {
		return invoiceRecordId;
	}
	public Integer getLp() {
		return lp;
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

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public void setInvoiceRecordId(final Integer invoiceRecordId) {
		this.invoiceRecordId = invoiceRecordId;
	}

	public void setLp(final Integer lp) {
		this.lp = lp;
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
