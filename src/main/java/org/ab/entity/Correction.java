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

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="Correction")
public class Correction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="correctionId")
	private Integer correctionId;

	@ManyToOne
	@JoinColumn(name="invoiceId")
	private Invoice invoice;

	@Column(name="correctionNumber")
	private String correctionNumber;

	@Column(name="createDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate createDate;

	@Column(name="receiveDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate receiveDate;

//	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
//	@JoinColumn(name="invoiceId")
//	private List<InvoiceRecord> invoiceRecords;

	@Column(name="netAmount")
	private BigDecimal netAmount;

	@Column(name="vatAmount")
	private BigDecimal vatAmount;

	@Column(name="grossAmount")
	private BigDecimal grossAmount;

	@Column(name="grossAmountWords")
	private String grossAmountWords;

	@Column(name="paymentDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate paymentDate;

//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.ALL)
//	private InvoiceContent invoiceContent;
//
//	@OneToMany(mappedBy="invoice")
//	private List<InvoicePayment> invoicePayments;

	public Integer getCorrectionId() {
		return correctionId;
	}

	public void setCorrectionId(final Integer correctionId) {
		this.correctionId = correctionId;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public String getCorrectionNumber() {
		return correctionNumber;
	}

	public void setCorrectionNumber(final String correctionNumber) {
		this.correctionNumber = correctionNumber;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(final LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(final BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public String getGrossAmountWords() {
		return grossAmountWords;
	}

	public void setGrossAmountWords(final String grossAmountWords) {
		this.grossAmountWords = grossAmountWords;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(final LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

}
