package org.ab.model;

import java.math.BigDecimal;
import java.util.List;

import org.ab.service.generator.CorrectionServiceRecord;
import org.ab.service.generator.InvoiceServiceRecord;
import org.ab.util.DecimalWriter;
import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

public class CorrectionModel implements PrintableContent {

	public static class Builder {
		private InvoiceModel invoice;
		private Integer correctionId;
		private String correctionNumber;
		private String dateHeader;
		private LocalDate createDate;
		private LocalDate receiveDate;
		private final List<CorrectionServiceRecord> serviceRecords = Lists.newArrayList();
		private BigDecimal netAmount;
		private BigDecimal vatAmount;
		private BigDecimal grossAmount;
		private BigDecimal netAmountDiff;
		private BigDecimal vatAmountDiff;
		private BigDecimal grossAmountDiff;
		private String grossAmountDiffWords;
		private LocalDate paymentDate;
		private String htmlContent;

		public CorrectionModel build(){
			return new CorrectionModel(this);
		}

		private List<CorrectionServiceRecord> createServiceRecords(
				final List<InvoiceServiceRecord> invoiceServiceRecords) {
			return Lists.newArrayList(FluentIterable.from(invoiceServiceRecords).transform(
					new Function<InvoiceServiceRecord, CorrectionServiceRecord>(){
						@Override
						public CorrectionServiceRecord apply(final InvoiceServiceRecord invoiceRecord) {
							return new CorrectionServiceRecord.Builder()
							.fromInvoiceServiceRecord(invoiceRecord)
							.build();
						}
					}).toList());
		}

		public Builder fromInvoice(final InvoiceModel invoice) {
			final LocalDate currentDate = LocalDate.now();
			this.invoice = invoice;
			createDate = currentDate;
			dateHeader = invoice.getDateHeader();
			paymentDate = currentDate.plusWeeks(2);
			receiveDate = currentDate;
			netAmount = invoice.getNetAmount();
			vatAmount = invoice.getVatAmount();
			grossAmount = invoice.getGrossAmount();
			netAmountDiff = BigDecimal.ZERO.setScale(2);
			vatAmountDiff = BigDecimal.ZERO.setScale(2);
			grossAmountDiff = BigDecimal.ZERO.setScale(2);
			grossAmountDiffWords = DecimalWriter.getDecimalSpoken(grossAmountDiff.toPlainString());
			serviceRecords.addAll(createServiceRecords(invoice.getServiceRecords()));

			return this;
		}

		public Builder withCorrectionId(final Integer correctionId) {
			this.correctionId = correctionId;
			return this;
		}

		public Builder withCorrectionNumber(final String correctionNumber){
			this.correctionNumber = correctionNumber;
			return this;
		}

		public Builder withCreateDate(final LocalDate createDate) {
			this.createDate = createDate;
			return this;
		}

		public Builder withDateHeader(final String dateHeader) {
			this.dateHeader = dateHeader;
			return this;
		}

		public Builder withGrossAmount(final BigDecimal grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withGrossAmountDiff(final BigDecimal grossAmountDiff){
			this.grossAmountDiff = grossAmountDiff;
			return this;
		}

		public Builder withGrossAmountDiffWords(final String grossAmountDiffWords) {
			this.grossAmountDiffWords = grossAmountDiffWords;
			return this;
		}

		public Builder withHtmlContent(final String invoiceHtml) {
			htmlContent = invoiceHtml;
			return this;
		}

		public Builder withInvoice(final InvoiceModel invoice) {
			this.invoice = invoice;
			return this;
		}

		public Builder withNetAmount(final BigDecimal netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withNetAmountDiff(final BigDecimal netAmountDiff){
			this.netAmountDiff = netAmountDiff;
			return this;
		}

		public Builder withPaymentDate(final LocalDate date) {
			paymentDate = date;
			return this;
		}

		public Builder withReceiveDate(final LocalDate receiveDate) {
			this.receiveDate = receiveDate;
			return this;
		}

		public Builder withServiceRecord(final CorrectionServiceRecord serviceRecord) {
			serviceRecords.add(serviceRecord);
			return this;
		}

		public Builder withServiceRecords(final List<CorrectionServiceRecord> serviceRecords) {
			this.serviceRecords.addAll(serviceRecords);
			return this;
		}

		public Builder withVatAmount(final BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}

		public Builder withVatAmountDiff(final BigDecimal vatAmountDiff){
			this.vatAmountDiff = vatAmountDiff;
			return this;
		}
	}

	private InvoiceModel invoice;
	private Integer correctionId;
	private String correctionNumber;
	private String dateHeader;
	private LocalDate createDate;
	private LocalDate receiveDate;
	private List<CorrectionServiceRecord> serviceRecords = Lists.newArrayList();
	private BigDecimal netAmount;
	private BigDecimal vatAmount;
	private BigDecimal grossAmount;
	private BigDecimal netAmountDiff;
	private BigDecimal vatAmountDiff;
	private BigDecimal grossAmountDiff;
	private String grossAmountDiffWords;
	private LocalDate paymentDate;
	private String htmlContent;

	public CorrectionModel(){
	}

	public CorrectionModel(final Builder builder){
		invoice = builder.invoice;
		correctionNumber = builder.correctionNumber;
		dateHeader = builder.dateHeader;
		createDate = builder.createDate;
		receiveDate = builder.receiveDate;
		serviceRecords = builder.serviceRecords;
		netAmount = builder.netAmount;
		vatAmount = builder.vatAmount;
		grossAmount = builder.grossAmount;
		netAmountDiff = builder.netAmountDiff;
		vatAmountDiff = builder.vatAmountDiff;
		grossAmountDiff = builder.grossAmountDiff;
		grossAmountDiffWords = builder.grossAmountDiffWords;
		correctionId = builder.correctionId;
		paymentDate = builder.paymentDate;
		htmlContent = builder.htmlContent;
	}

	public Integer getCorrectionId() {
		return correctionId;
	}

	public String getCorrectionNumber() {
		return correctionNumber;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public String getDateHeader() {
		return dateHeader;
	}

	public BigDecimal getGrossAmount() {
		return grossAmount;
	}

	public BigDecimal getGrossAmountDiff() {
		return grossAmountDiff;
	}

	public String getGrossAmountDiffWords() {
		return grossAmountDiffWords;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public InvoiceModel getInvoice() {
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

	public List<CorrectionServiceRecord> getServiceRecords() {
		return serviceRecords;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public BigDecimal getVatAmountDiff() {
		return vatAmountDiff;
	}

	public void setCorrectionId(final Integer correctionId) {
		this.correctionId = correctionId;
	}

	public void setCorrectionNumber(final String correctionNumber) {
		this.correctionNumber = correctionNumber;
	}

	public void setCreateDate(final LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setDateHeader(final String dateHeader) {
		this.dateHeader = dateHeader;
	}

	public void setGrossAmount(final BigDecimal grossAmount) {
		this.grossAmount = grossAmount;
	}

	public void setGrossAmountDiff(final BigDecimal grossAmountDiff) {
		this.grossAmountDiff = grossAmountDiff;
	}

	public void setGrossAmountDiffWords(final String grossAmountDiffWords) {
		this.grossAmountDiffWords = grossAmountDiffWords;
	}

	public void setHtmlContent(final String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public void setInvoice(final InvoiceModel invoice) {
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

	public void setServiceRecords(final List<CorrectionServiceRecord> serviceRecords) {
		this.serviceRecords = serviceRecords;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public void setVatAmountDiff(final BigDecimal vatAmountDiff) {
		this.vatAmountDiff = vatAmountDiff;
	}

}
