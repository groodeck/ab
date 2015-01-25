package org.ab.model;

import java.util.List;

public class PaymentModel {

	private String paymentId;
	private SubscriberModel subscriber;
	private String paymentAmount;
	private List<InvoicePaymentModel> invoices;

	public List<InvoicePaymentModel> getInvoices() {
		return invoices;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public SubscriberModel getSubscriber() {
		return subscriber;
	}
	public void setInvoices(final List<InvoicePaymentModel> invoices) {
		this.invoices = invoices;
	}
	public void setPaymentAmount(final String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public void setPaymentId(final String paymentId) {
		this.paymentId = paymentId;
	}
	public void setSubscriber(final SubscriberModel subscriber) {
		this.subscriber = subscriber;
	}
}
