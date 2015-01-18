package org.ab.model;

import org.ab.service.generator.InvoiceParticipant;


public class InvoiceModel {

	public static class Builder {
		private String createDate;
		private String grossAmount;
		private String invoiceHtml;
		private String invoiceId;
		private String invoiceNumber;
		private String netAmount;
		private String receiveDate;
		private String paymentDate;
		private boolean paid;
		private String settlementPeriodEnd;
		private String settlementPeriodStart;
		private String vatAmount;
		private InvoiceParticipant subscriber;

		public InvoiceModel build() {
			return new InvoiceModel(this);
		}

		public Builder withCreateDate(final String createDate) {
			this.createDate = createDate;
			return this;
		}

		public Builder withGrossAmount(final String grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withHtmlContent(final String invoiceHtml) {
			this.invoiceHtml = invoiceHtml;
			return this;
		}

		public Builder withInvoiceId(final String invoiceId) {
			this.invoiceId = invoiceId;
			return this;
		}

		public Builder withInvoiceNumber(final String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
			return this;
		}

		public Builder withNetAmount(final String netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withPaid(final boolean paid) {
			this.paid = paid;
			return this;
		}

		public Builder withPaymentDate(final String paymentDate) {
			this.paymentDate = paymentDate;
			return this;
		}

		public Builder withReceiveDate(final String receiveDate) {
			this.receiveDate = receiveDate;
			return this;
		}

		public Builder withSettlementPeriodEnd(final String settlementPeriodEnd) {
			this.settlementPeriodEnd = settlementPeriodEnd;
			return this;
		}

		public Builder withSettlementPeriodStart(final String settlementPeriodStart) {
			this.settlementPeriodStart = settlementPeriodStart;
			return this;
		}

		public Builder withSubscriber(final InvoiceParticipant subscriber) {
			this.subscriber = subscriber;
			return this;
		}

		public Builder withVatAmount(final String vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}

	}
	private final String createDate;
	private final String grossAmount;
	private final String invoiceHtml;
	private final String invoiceId;
	private final String invoiceNumber;
	private final String netAmount;
	private final String receiveDate;
	private final String paymentDate;
	private final boolean paid;
	private final String settlementPeriodEnd;
	private final String settlementPeriodStart;
	private final String vatAmount;
	private final InvoiceParticipant subscriber;

	public InvoiceModel(final Builder builder) {
		createDate = builder.createDate;
		grossAmount = builder.grossAmount;
		invoiceHtml = builder.invoiceHtml;
		invoiceId = builder.invoiceId;
		invoiceNumber = builder.invoiceNumber;
		netAmount = builder.netAmount;
		receiveDate = builder.receiveDate;
		paymentDate = builder.paymentDate;
		paid = builder.paid;
		settlementPeriodEnd = builder.settlementPeriodEnd;
		settlementPeriodStart = builder.settlementPeriodStart;
		vatAmount = builder.vatAmount;
		subscriber = builder.subscriber;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getGrossAmount() {
		return grossAmount;
	}

	public String getInvoiceHtml() {
		return invoiceHtml;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public String getSettlementPeriodEnd() {
		return settlementPeriodEnd;
	}

	public String getSettlementPeriodStart() {
		return settlementPeriodStart;
	}

	public InvoiceParticipant getSubscriber() {
		return subscriber;
	}

	public String getVatAmount() {
		return vatAmount;
	}

	public boolean isPaid() {
		return paid;
	}

}
