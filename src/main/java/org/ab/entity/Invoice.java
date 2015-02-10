package org.ab.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="Invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invoiceId")
	private Integer invoiceId;

	@ManyToOne
	@JoinColumn(name="contractId")
	private Contract contract;

	@Column(name="settlementPeriodStart")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate settlementPeriodStart;

	@Column(name="settlementPeriodEnd")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate settlementPeriodEnd;

	@Column(name="invoiceNumber")
	private String invoiceNumber;

	@Column(name="createDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate createDate;

	@Column(name="receiveDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate receiveDate;

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="invoiceId")
	private List<InvoiceRecord> invoiceRecords;

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

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.ALL)
	private InvoiceContent invoiceContent;

	@OneToMany(mappedBy="invoice")
	private List<InvoicePayment> invoicePayments;

	public Contract getContract() {
		return contract;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public String getGrossAmountWords() {
		return grossAmountWords;
	}

	public InvoiceContent getInvoiceContent() {
		return invoiceContent;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public List<InvoicePayment> getInvoicePayments() {
		return invoicePayments;
	}

	public List<InvoiceRecord> getInvoiceRecords() {
		return invoiceRecords;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public LocalDate getReceiveDate() {
		return receiveDate;
	}

	public LocalDate getSettlementPeriodEnd() {
		return settlementPeriodEnd;
	}

	public LocalDate getSettlementPeriodStart() {
		return settlementPeriodStart;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public String isGrossAmountWords() {
		return grossAmountWords;
	}

	public void setContract(final Contract contract) {
		this.contract = contract;
	}

	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public void setGrossAmountWords(final String grossAmountWords) {
		this.grossAmountWords = grossAmountWords;
	}

	public void setInvoiceContent(final InvoiceContent invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public void setInvoiceId(final Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setInvoicePayments(final List<InvoicePayment> invoicePayments) {
		this.invoicePayments = invoicePayments;
	}

	public void setInvoiceRecords(final List<InvoiceRecord> invoiceRecords) {
		this.invoiceRecords = invoiceRecords;
	}

	public void setNetAmount(final BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public void setPaymentDate(final LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setReceiveDate(final LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setSettlementPeriodEnd(final LocalDate settlementPeriodEnd) {
		this.settlementPeriodEnd = settlementPeriodEnd;
	}

	public void setSettlementPeriodStart(final LocalDate settlementPeriodStart) {
		this.settlementPeriodStart = settlementPeriodStart;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}
}
