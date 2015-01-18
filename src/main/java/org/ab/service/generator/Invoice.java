package org.ab.service.generator;

import java.math.BigDecimal;
import java.util.List;

import org.ab.entity.Contract;
import org.joda.time.LocalDate;

import com.google.common.collect.Lists;

public class Invoice {
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

		public Invoice build(){
			return new Invoice(this);
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

		public Builder withVatAmount(final BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}
	}
	private final Integer invoiceId;
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

	private Invoice(final Builder builder){
		seller = builder.seller;
		buyer = builder.buyer;
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
		paid = builder.paid;
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

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setHtmlContent(final String html) {
		htmlContent = html;
	}
}
