package org.ab.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentId", unique = true, nullable = false)
	private Integer paymentId;

	@Column(name = "paymentAmount")
	private BigDecimal paymentAmount;

	@Column(name = "createDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate createDate;

	@OneToMany(mappedBy="payment")
	private List<InvoicePayment> invoicePayments;

	public LocalDate getCreateDate() {
		return createDate;
	}

	public List<InvoicePayment> getInvoicePayments() {
		return invoicePayments;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setInvoicePayments(final List<InvoicePayment> invoicePayments) {
		this.invoicePayments = invoicePayments;
	}

	public void setPaymentAmount(final BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public void setPaymentId(final Integer paymentId) {
		this.paymentId = paymentId;
	}

}
