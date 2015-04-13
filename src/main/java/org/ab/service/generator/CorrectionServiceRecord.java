package org.ab.service.generator;

import java.math.BigDecimal;

public class CorrectionServiceRecord {
	public static class Builder {
		private Integer correctionRecordId;
		private InvoiceServiceRecord invoiceRecord;
		private String serviceName;
		private Integer vatRate;
		private BigDecimal netPrice;
		private BigDecimal netPriceDiff;
		private BigDecimal netAmount;
		private BigDecimal netAmountDiff;
		private BigDecimal vatAmount;
		private BigDecimal vatAmountDiff;
		private BigDecimal grossAmount;
		private BigDecimal grossAmountDiff;
		private Integer quantity;
		private Integer quantityDiff;

		public CorrectionServiceRecord build(){
			return new CorrectionServiceRecord(this);
		}

		public Builder fromInvoiceServiceRecord(final InvoiceServiceRecord invoiceRecord){
			this.invoiceRecord = invoiceRecord;
			serviceName = invoiceRecord.getServiceName();
			vatRate = invoiceRecord.getVatRate();
			quantity = invoiceRecord.getQuantity();
			quantityDiff = 0;
			netPrice = invoiceRecord.getNetPrice();
			netPriceDiff = BigDecimal.ZERO.setScale(2);
			netAmount = invoiceRecord.getNetAmount();
			netAmountDiff = BigDecimal.ZERO.setScale(2);
			vatAmount = invoiceRecord.getVatAmount();
			vatAmountDiff = BigDecimal.ZERO.setScale(2);
			grossAmount = invoiceRecord.getGrossAmount();
			grossAmountDiff = BigDecimal.ZERO.setScale(2);
			return this;
		}

		public Builder withCorrectionRecordId(final Integer correctionRecordId) {
			this.correctionRecordId = correctionRecordId;
			return this;
		}

		public Builder withGrossAmount(final BigDecimal grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withGrossAmountDiff(final BigDecimal grossAmountDiff) {
			this.grossAmountDiff = grossAmountDiff;
			return this;
		}

		public Builder withInvoiceServiceRecord(final InvoiceServiceRecord invoiceRecord){
			this.invoiceRecord = invoiceRecord;
			return this;
		}

		public Builder withNetAmount(final BigDecimal netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withNetAmountDiff(final BigDecimal netAmountDiff) {
			this.netAmountDiff = netAmountDiff;
			return this;
		}

		public Builder withNetPrice(final BigDecimal netPrice) {
			this.netPrice = netPrice;
			return this;
		}

		public Builder withNetPriceDiff(final BigDecimal netPriceDiff) {
			this.netPriceDiff = netPriceDiff;
			return this;
		}

		public Builder withQuantity(final Integer quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder withQuantityDiff(final Integer quantityDiff) {
			this.quantityDiff = quantityDiff;
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

		public Builder withVatAmountDiff(final BigDecimal vatAmountDiff) {
			this.vatAmountDiff = vatAmountDiff;
			return this;
		}

		public Builder withVatRate(final Integer vatRate) {
			this.vatRate = vatRate;
			return this;
		}
	}

	private Integer correctionRecordId;
	private InvoiceServiceRecord invoiceRecord;
	private String serviceName;
	private Integer vatRate;
	private BigDecimal netAmount;
	private BigDecimal netAmountDiff;
	private BigDecimal netPrice;
	private BigDecimal netPriceDiff;
	private BigDecimal vatAmount;
	private BigDecimal vatAmountDiff;
	private BigDecimal grossAmount;
	private BigDecimal grossAmountDiff;
	private Integer quantity;
	private Integer quantityDiff;

	public CorrectionServiceRecord(){
	}

	public CorrectionServiceRecord(final Builder builder) {
		correctionRecordId = builder.correctionRecordId;
		serviceName = builder.serviceName;
		netPrice = builder.netPrice;
		netPriceDiff = builder.netPriceDiff;
		vatRate = builder.vatRate;
		netAmount = builder.netAmount;
		netAmountDiff = builder.netAmountDiff;
		vatAmount = builder.vatAmount;
		vatAmountDiff = builder.vatAmountDiff;
		grossAmount = builder.grossAmount;
		grossAmountDiff = builder.grossAmountDiff;
		quantity = builder.quantity;
		quantityDiff = builder.quantityDiff;
		invoiceRecord = builder.invoiceRecord;

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
	public InvoiceServiceRecord getInvoiceRecord() {
		return invoiceRecord;
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
	public void setCorrectionRecordId(final Integer correctionRecordId) {
		this.correctionRecordId = correctionRecordId;
	}
	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}
	public void setGrossAmountDiff(final BigDecimal grossAmountDiff) {
		this.grossAmountDiff = grossAmountDiff;
	}
	public void setInvoiceRecord(final InvoiceServiceRecord invoiceRecord) {
		this.invoiceRecord = invoiceRecord;
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
