package org.ab.service.generator;

import java.util.List;

import com.google.common.collect.Lists;

public class Invoice {
	private final InvoiceParticipant seller;
	private final InvoiceParticipant buyer;
	private final String invoiceNumber;
	private final String dateHeader;
	private final String createDate;
	private final String receiveDate;
	private final List<InvoiceServiceRecord> serviceRecords;
	private final String netAmount;
	private final String vatAmount;
	private final String grossAmount;
	private final String grossAmountWords;

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

	}

	public static class Builder {
		private InvoiceParticipant seller;
		private InvoiceParticipant buyer;
		private String invoiceNumber;
		private String dateHeader;
		private String createDate;
		private String receiveDate;
		private final List<InvoiceServiceRecord> serviceRecords = Lists.newArrayList();
		private String netAmount;
		private String vatAmount;
		private String grossAmount;
		private String grossAmountWords;

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

		public Builder withCreateDate(final String createDate) {
			this.createDate = createDate;
			return this;
		}

		public Builder withReceiveDate(final String receiveDate) {
			this.receiveDate = receiveDate;
			return this;
		}

		public Builder withServiceRecord(final InvoiceServiceRecord serviceRecord) {
			this.serviceRecords.add(serviceRecord);
			return this;
		}

		public Builder withNetAmount(final String netAmount) {
			this.netAmount = netAmount;
			return this;
		}

		public Builder withVatAmount(final String vatAmount) {
			this.vatAmount = vatAmount;
			return this;
		}

		public Builder withGrossAmount(final String grossAmount) {
			this.grossAmount = grossAmount;
			return this;
		}

		public Builder withGrossAmountWords(final String grossAmountWords) {
			this.grossAmountWords = grossAmountWords;
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

	public String getCreateDate() {
		return this.createDate;
	}

	public String getReceiveDate() {
		return this.receiveDate;
	}

	public List<InvoiceServiceRecord> getServiceRecords() {
		return this.serviceRecords;
	}

	public String getNetAmount() {
		return this.netAmount;
	}

	public String getVatAmount() {
		return this.vatAmount;
	}

	public String getGrossAmount() {
		return this.grossAmount;
	}

	public String getGrossAmountWords() {
		return this.grossAmountWords;
	}
}
