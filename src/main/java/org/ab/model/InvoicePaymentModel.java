package org.ab.model;

public class InvoicePaymentModel {

	private String paymentId;
	private String invoiceId;
	private String invoiceNumber;
	private String settlementPeriod;
	private String paymentAmount;
	private boolean shouldBePaid;

	public String getInvoiceId() {
		return invoiceId;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public String getSettlementPeriod() {
		return settlementPeriod;
	}
	public boolean isShouldBePaid() {
		return shouldBePaid;
	}
	public void setInvoiceId(final String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public void setPaymentAmount(final String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public void setPaymentId(final String paymentId) {
		this.paymentId = paymentId;
	}
	public void setSettlementPeriod(final String settlementPeriod) {
		this.settlementPeriod = settlementPeriod;
	}
	public void setShouldBePaid(final boolean shouldBePaid) {
		this.shouldBePaid = shouldBePaid;
	}
}
