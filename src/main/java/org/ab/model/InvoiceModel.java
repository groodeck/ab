package org.ab.model;

import java.math.BigDecimal;
import java.util.List;

import org.ab.entity.Contract;
import org.ab.service.generator.InvoiceParticipant;
import org.ab.service.generator.InvoiceServiceRecord;
import org.joda.time.LocalDate;

import com.google.common.collect.Lists;

public class InvoiceModel {
	public static class Builder {
		private Integer invoiceId;
		private InvoiceParticipant seller;
		private InvoiceParticipant buyer;
		private String invoiceNumber;
		private String dateHeader;
		private LocalDate createDate;
		private LocalDate receiveDate;
		private final List<InvoiceServiceRecord> serviceRecords = Lists.newArrayList();
		private BigDecimal netAmount;
		private BigDecimal vatAmount;
		private BigDecimal grossAmount;
		private String grossAmountWords;
		private Contract contract;
		private LocalDate settlementPeriodStart;
		private LocalDate settlementPeriodEnd;
		private LocalDate paymentDate;
		private boolean paid;
		private String htmlContent;

		public InvoiceModel build(){
			return new InvoiceModel(this);
		}

		public Builder withBuyer(final InvoiceParticipant buyer) {
			this.buyer = buyer;
			return this;
		}

		public Builder withContract(final Contract contract) {
			this.contract = contract;
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

		public Builder withInvoiceId(final Integer invoiceId) {
			this.invoiceId = invoiceId;
			return this;
		}

		public Builder withInvoiceNumber(final String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
			return this;
		}

		public Builder withNetAmount(final BigDecimal netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withPaid(final boolean paid) {
			this.paid = paid;
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

		public Builder withServiceRecord(final InvoiceServiceRecord serviceRecord) {
			this.serviceRecords.add(serviceRecord);
			return this;
		}

		public Builder withServiceRecords(final List<InvoiceServiceRecord> serviceRecords) {
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

		public Builder withVatAmount(final BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}
	}
	private Integer invoiceId;
	private final InvoiceParticipant seller;
	private final InvoiceParticipant buyer;
	private final String invoiceNumber;
	private final String dateHeader;
	private final LocalDate createDate;
	private final LocalDate receiveDate;
	private final List<InvoiceServiceRecord> serviceRecords;
	private final BigDecimal netAmount;
	private final BigDecimal vatAmount;
	private final BigDecimal grossAmount;
	private final String grossAmountWords;
	private final Contract contract;
	private final LocalDate settlementPeriodStart;
	private final LocalDate settlementPeriodEnd;
	private final LocalDate paymentDate;
	private final boolean paid;
	private String htmlContent;

	private InvoiceModel(final Builder builder){
		this.seller = builder.seller;
		this.buyer = builder.buyer;
		this.invoiceNumber = builder.invoiceNumber;
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
		this.invoiceId = builder.invoiceId;
		this.paymentDate = builder.paymentDate;
		this.htmlContent = builder.htmlContent;
		this.paid = builder.paid;
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

	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
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

	public List<InvoiceServiceRecord> getServiceRecords() {
		return this.serviceRecords;
	}

	public LocalDate getSettlementPeriodEnd() {
		return this.settlementPeriodEnd;
	}

	public LocalDate getSettlementPeriodStart() {
		return this.settlementPeriodStart;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public boolean isPaid() {
		return this.paid;
	}

	public void setInvoiceId(final Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setHtmlContent(final String htmlContent) {
		this.htmlContent = htmlContent;
	}
}
