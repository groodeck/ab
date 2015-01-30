package org.ab.model;

public class InvoicePaymentModel {

	private String paymentId;
	private String invoiceId;
	private String invoiceNumber;
	private String invoiceGrossAmount;
	private String invoiceLeftToPay;
	private String settlementPeriod;
	private String paymentAmount;
	private boolean shouldBePaid;

	public String getInvoiceId() {
		return this.invoiceId;
	}
	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}
	public String getPaymentAmount() {
		return this.paymentAmount;
	}
	public String getPaymentId() {
		return this.paymentId;
	}
	public String getSettlementPeriod() {
		return this.settlementPeriod;
	}
	public boolean isShouldBePaid() {
		return this.shouldBePaid;
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
	public String getInvoiceGrossAmount() {
		return this.invoiceGrossAmount;
	}
	public void setInvoiceGrossAmount(final String invoiceGrossAmount) {
		this.invoiceGrossAmount = invoiceGrossAmount;
	}
	public String getInvoiceLeftToPay() {
		return this.invoiceLeftToPay;
	}
	public void setInvoiceLeftToPay(final String invoiceLeftToPay) {
		this.invoiceLeftToPay = invoiceLeftToPay;
	}
}
