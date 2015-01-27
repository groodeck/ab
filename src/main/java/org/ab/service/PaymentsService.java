package org.ab.service;

import java.util.List;

import org.ab.dao.ContractDao;
import org.ab.dao.InvoiceDao;
import org.ab.entity.Invoice;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoicePaymentModel;
import org.ab.model.PaymentModel;
import org.ab.service.converter.InvoicePaymentConverter;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PaymentsService {

	@Autowired
	public ContractDao contractDao;

	@Autowired
	private InvoicePaymentConverter invoicePaymentConverter;

	@Autowired
	private InvoiceDao invoiceDao;

	public List<PaymentModel> findPayments(final String subscriberIdn, final LocalDate dateFrom, final LocalDate dateTo) {
		final List<org.ab.entity.Invoice> invoices = invoiceDao.findInvoices(subscriberIdn, dateFrom, dateTo);
		//TODO
		//return this.invoiceConverter.convertEntities(invoices);
		return null;
	}

	private LocalDate getFirstOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMinimumValue();
	}

	public String getInvoiceHtmlContent(final int invoiceId) {
		final org.ab.entity.Invoice invoice = invoiceDao.getInvoice(invoiceId);
		return invoice.getInvoiceContent().getInvoiceHtml();
	}

	private LocalDate getLastOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMaximumValue();
	}

	private LocalDate getLocalDate(final InvoiceGenerationParams generationParams) {
		return LocalDate.now()
				.withYear(Integer.parseInt(generationParams.getYear()))
				.withMonthOfYear(Integer.parseInt(generationParams.getMonth()));
	}

	public PaymentModel getPayment(final int paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<InvoicePaymentModel> getUnpaidInvoices(final String subscriberId) {
		final List<Invoice> invoices = invoiceDao.findUnpaidInvoices(subscriberId);
		return invoicePaymentConverter.convertEntities(invoices);
	}

	public void save(final PaymentModel payment, final String name) {
		// TODO Auto-generated method stub

	}
}
