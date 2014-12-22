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
    @JoinColumn(name="contractId", insertable=false, updatable=true, nullable=true)
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
	private boolean grossAmountWords;

	@Column(name="paymentDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate paymentDate;

	@Column(name="paid")
	private boolean paid;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.ALL)
	private InvoiceContent invoiceContent;

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(final Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public LocalDate getSettlementPeriodStart() {
		return this.settlementPeriodStart;
	}

	public void setSettlementPeriodStart(final LocalDate settlementPeriodStart) {
		this.settlementPeriodStart = settlementPeriodStart;
	}

	public LocalDate getSettlementPeriodEnd() {
		return this.settlementPeriodEnd;
	}

	public void setSettlementPeriodEnd(final LocalDate settlementPeriodEnd) {
		this.settlementPeriodEnd = settlementPeriodEnd;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public LocalDate getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(final LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}

	public List<InvoiceRecord> getInvoiceRecords() {
		return this.invoiceRecords;
	}

	public void setInvoiceRecords(final List<InvoiceRecord> invoiceRecords) {
		this.invoiceRecords = invoiceRecords;
	}

	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(final BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public BigDecimal getGrossAmount() {
		return this.grossAmount;
	}

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public boolean isGrossAmountWords() {
		return this.grossAmountWords;
	}

	public void setGrossAmountWords(final boolean grossAmountWords) {
		this.grossAmountWords = grossAmountWords;
	}

	public LocalDate getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(final LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public boolean isPaid() {
		return this.paid;
	}

	public void setPaid(final boolean paid) {
		this.paid = paid;
	}

	public InvoiceContent getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(final InvoiceContent invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(final Contract contract) {
		this.contract = contract;
	}
}
