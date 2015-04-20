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

	@OneToMany(cascade={CascadeType.ALL}, orphanRemoval=true)
	@JoinColumn(name="correctionId")
	private List<CorrectionRecord> correctionRecords;

	@Column(name="netAmount")
	private BigDecimal netAmount;

	@Column(name="netAmountDiff")
	private BigDecimal netAmountDiff;

	@Column(name="vatAmount")
	private BigDecimal vatAmount;

	@Column(name="vatAmountDiff")
	private BigDecimal vatAmountDiff;

	@Column(name="grossAmount")
	private BigDecimal grossAmount;

	@Column(name="grossAmountDiff")
	private BigDecimal grossAmountDiff;

	@Column(name="grossAmountWords")
	private String grossAmountWords;

	@Column(name="paymentDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate paymentDate;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "correction", cascade = CascadeType.ALL)
	private CorrectionContent correctionContent;

	//	@OneToMany(mappedBy="invoice")
	//	private List<InvoicePayment> invoicePayments;

	public CorrectionContent getCorrectionContent() {
		return correctionContent;
	}

	public Integer getCorrectionId() {
		return correctionId;
	}

	public String getCorrectionNumber() {
		return correctionNumber;
	}

	public List<CorrectionRecord> getCorrectionRecords() {
		return correctionRecords;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public BigDecimal getGrossAmountDiff() {
		return grossAmountDiff;
	}

	public String getGrossAmountWords() {
		return grossAmountWords;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public BigDecimal getNetAmountDiff() {
		return netAmountDiff;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public LocalDate getReceiveDate() {
		return receiveDate;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public BigDecimal getVatAmountDiff() {
		return vatAmountDiff;
	}

	public void setCorrectionContent(final CorrectionContent correctionContent) {
		this.correctionContent = correctionContent;
	}

	public void setCorrectionId(final Integer correctionId) {
		this.correctionId = correctionId;
	}

	public void setCorrectionNumber(final String correctionNumber) {
		this.correctionNumber = correctionNumber;
	}

	public void setCorrectionRecords(final List<CorrectionRecord> correctionRecords) {
		this.correctionRecords = correctionRecords;
	}

	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public void setGrossAmountDiff(final BigDecimal grossAmountDiff) {
		this.grossAmountDiff = grossAmountDiff;
	}

	public void setGrossAmountWords(final String grossAmountWords) {
		this.grossAmountWords = grossAmountWords;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public void setNetAmount(final BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public void setNetAmountDiff(final BigDecimal netAmountDiff) {
		this.netAmountDiff = netAmountDiff;
	}

	public void setPaymentDate(final LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setReceiveDate(final LocalDate receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public void setVatAmountDiff(final BigDecimal vatAmountDiff) {
		this.vatAmountDiff = vatAmountDiff;
	}

}
