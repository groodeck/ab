package org.ab.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class InvoicePayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoicePaymentId", unique = true, nullable = false)
	private Integer invoicePaymentId;

	@Column(name = "paymentAmount")
	private BigDecimal paymentAmount;

	@ManyToOne
	@JoinColumn(name="paymentId")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name="invoiceId")
	private Invoice invoice;

	public Invoice getInvoice() {
		return invoice;
	}

	public Integer getInvoicePaymentId() {
		return invoicePaymentId;
	}

	public Payment getPayment() {
		return payment;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public void setInvoicePaymentId(final Integer invoicePaymentId) {
		this.invoicePaymentId = invoicePaymentId;
	}

	public void setPayment(final Payment payment) {
		this.payment = payment;
	}

	public void setPaymentAmount(final BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
}
