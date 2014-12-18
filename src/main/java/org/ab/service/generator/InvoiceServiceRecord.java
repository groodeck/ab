package org.ab.service.generator;

import java.math.BigDecimal;

public class InvoiceServiceRecord {
	private final Integer lp;
	private final String serviceName;
	private final Integer vatRate;
	private final BigDecimal netAmount;
	private final BigDecimal vatAmount;
	private final BigDecimal grossAmount;
	private final Integer quantity;

	public InvoiceServiceRecord(final Builder builder) {
		this.lp = builder.lp;
		this.serviceName = builder.serviceName;
		this.vatRate = builder.vatRate;
		this.netAmount = builder.netAmount;
		this.vatAmount = builder.vatAmount;
		this.grossAmount = builder.grossAmount;
		this.quantity = builder.quantity;
	}
	public Integer getLp() {
		return this.lp;
	}
	public String getServiceName() {
		return this.serviceName;
	}
	public Integer getVatRate() {
		return this.vatRate;
	}
	public BigDecimal getNetAmount() {
		return this.netAmount;
	}
	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}
	public BigDecimal getGrossAmount() {
		return this.grossAmount;
	}
	public Integer getQuantity() {
		return this.quantity;
	}

	public static class Builder {
		private Integer lp;
		private String serviceName;
		private Integer vatRate;
		private BigDecimal netAmount;
		private BigDecimal vatAmount;
		private BigDecimal grossAmount;
		private Integer quantity;

		public InvoiceServiceRecord build(){
			return new InvoiceServiceRecord(this);
		}

		public Builder withLp(final Integer lp) {
			this.lp = lp;
			return this;
		}

		public Builder withServiceName(final String serviceName) {
			this.serviceName = serviceName;
			return this;
		}

		public Builder withVatRate(final Integer vatRate) {
			this.vatRate = vatRate;
			return this;
		}

		public Builder withNetAmount(final BigDecimal netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withVatAmount(final BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}

		public Builder withGrossAmount(final BigDecimal grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withQuantity(final Integer quantity) {
			this.quantity = quantity;
			return this;
		}
	}
}
