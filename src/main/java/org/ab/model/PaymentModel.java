package org.ab.model;

import java.util.List;

import org.assertj.core.util.Lists;

public class PaymentModel {

	private String paymentId;
	private SubscriberModel subscriber;
	private String paymentAmount;
	private String createDate;
	private List<InvoicePaymentModel> invoices = Lists.newArrayList();

	public String getCreateDate() {
		return createDate;
	}
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
	public void setCreateDate(final String createDate) {
		this.createDate = createDate;
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
