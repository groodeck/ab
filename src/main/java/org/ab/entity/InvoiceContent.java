package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="InvoiceContent")
public class InvoiceContent {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "invoiceContentId", unique = true, nullable = false)
	private Integer invoiceContentId;

	@OneToOne(fetch = FetchType.LAZY)
	private Invoice invoice;

	@Column(name="invoiceHtml")
	private String invoiceHtml;

	public Integer getInvoiceContentId() {
		return this.invoiceContentId;
	}

	public void setInvoiceContentId(final Integer invoiceContentId) {
		this.invoiceContentId = invoiceContentId;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public String getInvoiceHtml() {
		return this.invoiceHtml;
	}

	public void setInvoiceHtml(final String invoiceHtml) {
		this.invoiceHtml = invoiceHtml;
	}


}
