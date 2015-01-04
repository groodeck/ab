package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="InvoiceContent")
public class InvoiceContent {

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "invoice"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "invoiceId", unique = true, nullable = false)
	private Integer invoiceId;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Invoice invoice;

	@Column(name="invoiceHtml")
	private String invoiceHtml;

	public Invoice getInvoice() {
		return invoice;
	}

	public String getInvoiceHtml() {
		return invoiceHtml;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoice(final Invoice invoice) {
		this.invoice = invoice;
	}

	public void setInvoiceHtml(final String invoiceHtml) {
		this.invoiceHtml = invoiceHtml;
	}

	public void setInvoiceId(final Integer invoiceId) {
		this.invoiceId = invoiceId;
	}


}
