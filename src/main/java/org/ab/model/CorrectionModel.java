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
			final LocalDate currentDate = LocalDate.now();
			this.invoice = invoice;
			this.contract = invoice.getContract();
			this.buyer = invoice.getBuyer();
			this.seller = invoice.getSeller();
			this.subscriberIdn = invoice.getSubscriberIdn();
			this.createDate = currentDate;
			this.dateHeader = invoice.getDateHeader();
			this.paymentDate = currentDate.plusWeeks(2);
			this.receiveDate = invoice.getReceiveDate();
			this.settlementPeriodStart = invoice.getSettlementPeriodStart();
			this.settlementPeriodEnd = invoice.getSettlementPeriodEnd();
			this.netAmount = invoice.getNetAmount();
			this.vatAmount = invoice.getVatAmount();
			this.grossAmount = invoice.getGrossAmount();
			this.serviceRecords.addAll(createServiceRecords(invoice.getServiceRecords()));

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
			this.htmlContent = invoiceHtml;
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
			this.paymentDate = date;
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
			this.serviceRecords.add(serviceRecord);
			return this;
		}

		public Builder withServiceRecords(final List<CorrectionServiceRecord> serviceRecords) {
			this.serviceRecords.addAll(serviceRecords);
			return this;
		}

		public Builder withSettlementPeriodEnd(final LocalDate date) {
			this.settlementPeriodEnd = date;
			return this;
		}

		public Builder withSettlementPeriodStart(final LocalDate date) {
			this.settlementPeriodStart = date;
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
		this.invoice = builder.invoice;
		this.subscriberIdn = builder.subscriberIdn;
		this.seller = builder.seller;
		this.buyer = builder.buyer;
		this.correctionNumber = builder.correctionNumber;
		this.dateHeader = builder.dateHeader;
		this.createDate = builder.createDate;
		this.receiveDate = builder.receiveDate;
		this.serviceRecords = builder.serviceRecords;
		this.netAmount = builder.netAmount;
		this.vatAmount = builder.vatAmount;
		this.grossAmount = builder.grossAmount;
		this.grossAmountWords = builder.grossAmountWords;
		this.contract = builder.contract;
		this.settlementPeriodStart = builder.settlementPeriodStart;
		this.settlementPeriodEnd = builder.settlementPeriodEnd;
		this.correctionId = builder.correctionId;
		this.paymentDate = builder.paymentDate;
		this.htmlContent = builder.htmlContent;
	}

	public InvoiceParticipant getBuyer() {
		return this.buyer;
	}

	public Contract getContract() {
		return this.contract;
	}

	public LocalDate getCreateDate() {
		return this.createDate;
	}

	public String getDateHeader() {
		return this.dateHeader;
	}

	public BigDecimal getGrossAmount() {
		return this.grossAmount;
	}

	public String getGrossAmountWords() {
		return this.grossAmountWords;
	}

	public String getHtmlContent() {
		return this.htmlContent;
	}

	public InvoiceModel getInvoice() {
		return this.invoice;
	}

	public Integer getInvoiceId() {
		return this.correctionId;
	}

	public String getInvoiceNumber() {
		return this.correctionNumber;
	}

	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public LocalDate getPaymentDate() {
		return this.paymentDate;
	}

	public LocalDate getReceiveDate() {
		return this.receiveDate;
	}

	public InvoiceParticipant getSeller() {
		return this.seller;
	}

	public List<CorrectionServiceRecord> getServiceRecords() {
		return this.serviceRecords;
	}

	public LocalDate getSettlementPeriodEnd() {
		return this.settlementPeriodEnd;
	}

	public LocalDate getSettlementPeriodStart() {
		return this.settlementPeriodStart;
	}

	public String getSubscriberIdn() {
		return this.subscriberIdn;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public void setHtmlContent(final String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public void setInvoiceId(final Integer invoiceId) {
		this.correctionId = invoiceId;
	}
}
