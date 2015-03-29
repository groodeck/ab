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
		private String subscriberIdn;
		private InvoiceParticipant seller;
		private InvoiceParticipant subscriber;
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
		private String htmlContent;

		public InvoiceModel build(){
			return new InvoiceModel(this);
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
			htmlContent = invoiceHtml;
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

		public Builder withServiceRecord(final InvoiceServiceRecord serviceRecord) {
			serviceRecords.add(serviceRecord);
			return this;
		}

		public Builder withServiceRecords(final List<InvoiceServiceRecord> serviceRecords) {
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

		public Builder withSubscriber(final InvoiceParticipant subscriber) {
			this.subscriber = subscriber;
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

	private Integer invoiceId;
	private String subscriberIdn;
	private InvoiceParticipant seller;
	private InvoiceParticipant subscriber;
	private String invoiceNumber;
	private String dateHeader;
	private LocalDate createDate;
	private LocalDate receiveDate;
	private List<InvoiceServiceRecord> serviceRecords;
	private BigDecimal netAmount;
	private BigDecimal vatAmount;
	private BigDecimal grossAmount;
	private String grossAmountWords;
	private Contract contract;
	private LocalDate settlementPeriodStart;
	private LocalDate settlementPeriodEnd;
	private LocalDate paymentDate;
	private String htmlContent;

	public InvoiceModel(){
	}

	private InvoiceModel(final Builder builder){
		subscriberIdn = builder.subscriberIdn;
		seller = builder.seller;
		subscriber = builder.subscriber;
		invoiceNumber = builder.invoiceNumber;
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
		invoiceId = builder.invoiceId;
		paymentDate = builder.paymentDate;
		htmlContent = builder.htmlContent;
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

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
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

	public List<InvoiceServiceRecord> getServiceRecords() {
		return serviceRecords;
	}

	public LocalDate getSettlementPeriodEnd() {
		return settlementPeriodEnd;
	}

	public LocalDate getSettlementPeriodStart() {
		return settlementPeriodStart;
	}

	public InvoiceParticipant getSubscriber() {
		return subscriber;
	}

	public String getSubscriberIdn() {
		return subscriberIdn;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setContract(final Contract contract) {
		this.contract = contract;
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

	public void setGrossAmountWords(final String grossAmountWords) {
		this.grossAmountWords = grossAmountWords;
	}

	public void setHtmlContent(final String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public void setInvoiceId(final Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setInvoiceNumber(final String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
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

	public void setSeller(final InvoiceParticipant seller) {
		this.seller = seller;
	}

	public void setServiceRecords(final List<InvoiceServiceRecord> serviceRecords) {
		this.serviceRecords = serviceRecords;
	}

	public void setSettlementPeriodEnd(final LocalDate settlementPeriodEnd) {
		this.settlementPeriodEnd = settlementPeriodEnd;
	}

	public void setSettlementPeriodStart(final LocalDate settlementPeriodStart) {
		this.settlementPeriodStart = settlementPeriodStart;
	}

	public void setSubscriber(final InvoiceParticipant subscriber) {
		this.subscriber = subscriber;
	}

	public void setSubscriberIdn(final String subscriberIdn) {
		this.subscriberIdn = subscriberIdn;
	}

	public void setVatAmount(final BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}
}
