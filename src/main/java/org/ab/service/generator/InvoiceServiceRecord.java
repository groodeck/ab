package org.ab.service.generator;

public class InvoiceServiceRecord {
	private final String lp;
	private final String serviceName;
	private final String vatRate;
	private final String netAmount;
	private final String vatAmount;
	private final String grossAmount;
	private final String quantity;

	public InvoiceServiceRecord(final Builder builder) {
		this.lp = builder.lp;
		this.serviceName = builder.serviceName;
		this.vatRate = builder.vatRate;
		this.netAmount = builder.netAmount;
		this.vatAmount = builder.vatAmount;
		this.grossAmount = builder.grossAmount;
		this.quantity = builder.quantity;
	}
	public String getLp() {
		return this.lp;
	}
	public String getServiceName() {
		return this.serviceName;
	}
	public String getVatRate() {
		return this.vatRate;
	}
	public String getNetAmount() {
		return this.netAmount;
	}
	public String getVatAmount() {
		return this.vatAmount;
	}
	public String getGrossAmount() {
		return this.grossAmount;
	}
	public String getQuantity() {
		return this.quantity;
	}

	public static class Builder {
		private String lp;
		private String serviceName;
		private String vatRate;
		private String netAmount;
		private String vatAmount;
		private String grossAmount;
		private String quantity;

		public InvoiceServiceRecord build(){
			return new InvoiceServiceRecord(this);
		}

		public Builder withLp(final String lp) {
			this.lp = lp;
			return this;
		}

		public Builder withServiceName(final String serviceName) {
			this.serviceName = serviceName;
			return this;
		}

		public Builder withVatRate(final String vatRate) {
			this.vatRate = vatRate;
			return this;
		}

		public Builder withNetAmount(final String netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withVatAmount(final String vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}

		public Builder withGrossAmount(final String grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withQuantity(final String quantity) {
			this.quantity = quantity;
			return this;
		}
	}
}
