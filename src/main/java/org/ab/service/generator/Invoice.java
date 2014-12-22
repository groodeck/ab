package org.ab.service.generator;

import java.math.BigDecimal;
import java.util.List;

import org.ab.entity.Contract;
import org.joda.time.LocalDate;

import com.google.common.collect.Lists;

public class Invoice {
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
	private String htmlContent;

	private Invoice(final Builder builder){
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

	}

	public static class Builder {
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

		public Invoice build(){
			return new Invoice(this);
		}

		public Builder withSeller(final InvoiceParticipant seller) {
			this.seller = seller;
			return this;
		}

		public Builder withBuyer(final InvoiceParticipant buyer) {
			this.buyer = buyer;
			return this;
		}

		public Builder withInvoiceNumber(final String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
			return this;
		}

		public Builder withDateHeader(final String dateHeader) {
			this.dateHeader = dateHeader;
			return this;
		}

		public Builder withCreateDate(final LocalDate createDate) {
			this.createDate = createDate;
			return this;
		}

		public Builder withReceiveDate(final LocalDate receiveDate) {
			this.receiveDate = receiveDate;
			return this;
		}

		public Builder withServiceRecord(final InvoiceServiceRecord serviceRecord) {
			this.serviceRecords.add(serviceRecord);
			return this;
		}

		public Builder withNetAmount(final BigDecimal netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withVatAmount(final BigDecimal vatAmount) {
			this.vatAmount = vatAmount;
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

		public Builder withContract(final Contract contract) {
			this.contract = contract;
			return this;
		}

		public Builder withSettlementPeriodStart(final LocalDate date) {
			this.settlementPeriodStart = date;
			return this;
		}

		public Builder withSettlementPeriodEnd(final LocalDate date) {
			this.settlementPeriodEnd = date;
			return this;
		}
	}

	public InvoiceParticipant getSeller() {
		return this.seller;
	}

	public InvoiceParticipant getBuyer() {
		return this.buyer;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public String getDateHeader() {
		return this.dateHeader;
	}

	public LocalDate getCreateDate() {
		return this.createDate;
	}

	public LocalDate getReceiveDate() {
		return this.receiveDate;
	}

	public List<InvoiceServiceRecord> getServiceRecords() {
		return this.serviceRecords;
	}

	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	public BigDecimal getGrossAmount() {
		return this.grossAmount;
	}

	public String getGrossAmountWords() {
		return this.grossAmountWords;
	}

	public void setHtmlContent(final String html) {
		this.htmlContent = html;
	}

	public String getHtmlContent() {
		return this.htmlContent;
	}

	public Contract getContract() {
		return this.contract;
	}

	public LocalDate getSettlementPeriodEnd() {
		return this.settlementPeriodEnd;
	}

	public LocalDate getSettlementPeriodStart() {
		return this.settlementPeriodStart;
	}
}
