package org.ab.service.generator;

import java.math.BigDecimal;

public class CorrectionServiceRecord {
	public static class Builder {
		private InvoiceServiceRecord invoiceRecord;
		private Integer lp;
		private String serviceName;
		private Integer vatRate;
		private BigDecimal netPrice;
		private BigDecimal netAmount;
		private BigDecimal vatAmount;
		private BigDecimal grossAmount;
		private Integer quantity;

		public CorrectionServiceRecord build(){
			return new CorrectionServiceRecord(this);
		}

		public Builder fromInvoiceServiceRecord(final InvoiceServiceRecord invoiceRecord){
			this.invoiceRecord = invoiceRecord;
			lp = invoiceRecord.getLp();
			serviceName = invoiceRecord.getServiceName();
			vatRate = invoiceRecord.getVatRate();
			quantity = invoiceRecord.getQuantity();
			netPrice = invoiceRecord.getNetPrice();
			netAmount = invoiceRecord.getNetAmount();
			vatAmount = invoiceRecord.getVatAmount();
			grossAmount = invoiceRecord.getGrossAmount();
			return this;
		}

		public Builder withGrossAmount(final BigDecimal grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withInvoiceServiceRecord(final InvoiceServiceRecord invoiceRecord){
			this.invoiceRecord = invoiceRecord;
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

	private final InvoiceServiceRecord invoiceRecord;
	private final Integer lp;
	private final String serviceName;
	private final Integer vatRate;
	private final BigDecimal netAmount;
	private final BigDecimal netPrice;
	private final BigDecimal vatAmount;
	private final BigDecimal grossAmount;

	private final Integer quantity;
	public CorrectionServiceRecord(final Builder builder) {
		lp = builder.lp;
		serviceName = builder.serviceName;
		netPrice = builder.netPrice;
		vatRate = builder.vatRate;
		netAmount = builder.netAmount;
		vatAmount = builder.vatAmount;
		grossAmount = builder.grossAmount;
		quantity = builder.quantity;
		invoiceRecord = builder.invoiceRecord;
	}
	public BigDecimal getGrossAmount() {
		return grossAmount;
	}
	public InvoiceServiceRecord getInvoiceRecord() {
		return invoiceRecord;
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
}
