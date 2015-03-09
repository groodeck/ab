package org.ab.model;

import java.math.BigDecimal;
import java.util.List;

import org.ab.entity.Contract;
import org.ab.service.generator.CorrectionServiceRecord;
import org.ab.service.generator.InvoiceParticipant;
import org.ab.service.generator.InvoiceServiceRecord;
import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

public class CorrectionModel {

	public static class Builder {
		private InvoiceModel invoice;
		private Integer correctionId;
		private String subscriberIdn;
		private InvoiceParticipant seller;
		private InvoiceParticipant buyer;
		private String correctionNumber;
		private String dateHeader;
		private LocalDate createDate;
		private LocalDate receiveDate;
		private final List<CorrectionServiceRecord> serviceRecords = Lists.newArrayList();
		private BigDecimal netAmount;
		private BigDecimal vatAmount;
		private BigDecimal grossAmount;
		private String grossAmountWords;
		private Contract contract;
		private LocalDate settlementPeriodStart;
		private LocalDate settlementPeriodEnd;
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
					}));
		}

		public Builder fromInvoice(final InvoiceModel invoice) {
			this.invoice = invoice;
			contract = invoice.getContract();
			buyer = invoice.getBuyer();
			seller = invoice.getSeller();
			subscriberIdn = invoice.getSubscriberIdn();
			createDate = invoice.getCreateDate();
			dateHeader = invoice.getDateHeader();
			paymentDate = invoice.getPaymentDate();
			receiveDate = invoice.getReceiveDate();
			settlementPeriodStart = invoice.getSettlementPeriodStart();
			settlementPeriodEnd = invoice.getSettlementPeriodEnd();
			netAmount = invoice.getNetAmount();
			vatAmount = invoice.getVatAmount();
			grossAmount = invoice.getGrossAmount();
			serviceRecords.addAll(createServiceRecords(invoice.getServiceRecords()));

			return this;
		}

		public Builder withBuyer(final InvoiceParticipant buyer) {
			this.buyer = buyer;
			return this;
		}

		public Builder withContract(final Contract contract) {
			this.contract = contract;
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

		public Builder withGrossAmountWords(final String grossAmountWords) {
			this.grossAmountWords = grossAmountWords;
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

		public Builder withPaymentDate(final LocalDate date) {
			paymentDate = date;
			return this;
		}

		public Builder withReceiveDate(final LocalDate receiveDate) {
			this.receiveDate = receiveDate;
			return this;
		}

		public Builder withSeller(final InvoiceParticipant seller) {
			this.seller = seller;
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

		public Builder withSettlementPeriodEnd(final LocalDate date) {
			settlementPeriodEnd = date;
			return this;
		}

		public Builder withSettlementPeriodStart(final LocalDate date) {
			settlementPeriodStart = date;
			return this;
		}

		public Builder withSubscriberIdn(final String subscriberIdn) {
			this.subscriberIdn = subscriberIdn;
			return this;
		}

		public Builder withVatAmount(final BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}
	}

	private final InvoiceModel invoice;
	private Integer correctionId;
	private final String subscriberIdn;
	private final InvoiceParticipant seller;
	private final InvoiceParticipant buyer;
	private final String correctionNumber;
	private final String dateHeader;
	private final LocalDate createDate;
	private final LocalDate receiveDate;
	private final List<CorrectionServiceRecord> serviceRecords;
	private final BigDecimal netAmount;
	private final BigDecimal vatAmount;
	private final BigDecimal grossAmount;
	private final String grossAmountWords;
	private final Contract contract;
	private final LocalDate settlementPeriodStart;
	private final LocalDate settlementPeriodEnd;
	private final LocalDate paymentDate;
	private String htmlContent;

	private CorrectionModel(final Builder builder){
		invoice = builder.invoice;
		subscriberIdn = builder.subscriberIdn;
		seller = builder.seller;
		buyer = builder.buyer;
		correctionNumber = builder.correctionNumber;
		dateHeader = builder.dateHeader;
		createDate = builder.createDate;
		receiveDate = builder.receiveDate;
		serviceRecords = builder.serviceRecords;
		netAmount = builder.netAmount;
		vatAmount = builder.vatAmount;
		grossAmount = builder.grossAmount;
		grossAmountWords = builder.grossAmountWords;
		contract = builder.contract;
		settlementPeriodStart = builder.settlementPeriodStart;
		settlementPeriodEnd = builder.settlementPeriodEnd;
		correctionId = builder.correctionId;
		paymentDate = builder.paymentDate;
		htmlContent = builder.htmlContent;
	}

	public InvoiceParticipant getBuyer() {
		return buyer;
	}

	public Contract getContract() {
		return contract;
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

	public String getGrossAmountWords() {
		return grossAmountWords;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public InvoiceModel getInvoice() {
		return invoice;
	}

	public Integer getInvoiceId() {
		return correctionId;
	}

	public String getInvoiceNumber() {
		return correctionNumber;
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

	public InvoiceParticipant getSeller() {
		return seller;
	}

	public List<CorrectionServiceRecord> getServiceRecords() {
		return serviceRecords;
	}

	public LocalDate getSettlementPeriodEnd() {
		return settlementPeriodEnd;
	}

	public LocalDate getSettlementPeriodStart() {
		return settlementPeriodStart;
	}

	public String getSubscriberIdn() {
		return subscriberIdn;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setHtmlContent(final String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public void setInvoiceId(final Integer invoiceId) {
		correctionId = invoiceId;
	}
}
