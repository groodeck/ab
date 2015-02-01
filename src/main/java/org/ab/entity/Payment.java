package org.ab.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	private LocalDate createDate;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "payments")
	private Set<Invoice> invoices;

	public LocalDate getCreateDate() {
		return createDate;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
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

	public void setInvoices(final Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public void setPaymentAmount(final BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public void setPaymentId(final Integer paymentId) {
		this.paymentId = paymentId;
	}
}
