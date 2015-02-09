package org.ab.service;

import java.math.BigDecimal;
import java.util.List;

import org.ab.dao.InvoiceDao;
import org.ab.dao.InvoicePaymentDao;
import org.ab.dao.PaymentDao;
import org.ab.entity.Invoice;
import org.ab.entity.InvoicePayment;
import org.ab.entity.Payment;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoicePaymentModel;
import org.ab.model.PaymentModel;
import org.ab.service.converter.PaymentConverter;
import org.ab.util.Translator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PaymentsService {

	@Autowired
	public InvoiceDao invoiceDao;

	@Autowired
	private PaymentConverter paymentConverter;

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private InvoicePaymentDao invoicePaymentDao;

	public List<PaymentModel> findPayments(final String subscriberIdn, final LocalDate dateFrom, final LocalDate dateTo) {
		final List<Payment> payments = paymentDao.findPayments(subscriberIdn, dateFrom, dateTo);
		return paymentConverter.convertPaymentEntities(payments);
	}

	private LocalDate getFirstOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMinimumValue();
	}

	public String getInvoiceHtmlContent(final int invoiceId) {
		final org.ab.entity.Invoice invoice = invoiceDao.getInvoice(invoiceId);
		return invoice.getInvoiceContent().getInvoiceHtml();
	}

	private InvoicePayment getInvoicePayment(final Payment payment, final Invoice invoice) {
		return invoicePaymentDao.getByInvoiceAndPayment(invoice, payment);
	}

	private LocalDate getLastOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMaximumValue();
	}

	private LocalDate getLocalDate(final InvoiceGenerationParams generationParams) {
		return LocalDate.now()
				.withYear(Integer.parseInt(generationParams.getYear()))
				.withMonthOfYear(Integer.parseInt(generationParams.getMonth()));
	}

	private Payment getOrCreatePayment(final String paymentId) {
		if(paymentId == null){
			return new Payment();
		} else {
			return paymentDao.getPayment(Integer.parseInt(paymentId));
		}
	}

	public PaymentModel getPayment(final int paymentId) {
		final Payment payment = paymentDao.getPayment(paymentId);
		return paymentConverter.convertPaymentEntity(payment);
	}

	public List<InvoicePaymentModel> getUnpaidInvoices(final String subscriberId) {
		final List<Invoice> invoices = invoiceDao.findUnpaidInvoices(subscriberId);
		return paymentConverter.convertInvoiceEntities(invoices);
	}

	private InvoicePayment orCreate(final InvoicePayment invoicePayment) {
		if(invoicePayment == null){
			return new InvoicePayment();
		} else {
			return invoicePayment;
		}
	}

	public void save(final PaymentModel paymentModel, final String name) {
		final Payment payment = getOrCreatePayment(paymentModel.getPaymentId());
		payment.setCreateDate(Translator.toLocalDate(paymentModel.getCreateDate()));
		payment.setPaymentAmount(Translator.toAmount(paymentModel.getPaymentAmount()));
		saveInvoicePayments(payment, paymentModel.getInvoices());
	}

	private void saveInvoicePayments(final Payment payment, final List<InvoicePaymentModel> invoicesModel) {
		for(final InvoicePaymentModel invoiceModel : invoicesModel){
			final Invoice invoice = invoiceDao.getInvoice(Integer.parseInt(invoiceModel.getInvoiceId()));
			final InvoicePayment invoicePayment = getInvoicePayment(payment, invoice);
			if(invoiceModel.isShouldBePaid()){
				final BigDecimal invoicePaymentAmount = Translator.toAmount(invoiceModel.getPaymentAmount());
				final InvoicePayment newInvoicePayment = orCreate(invoicePayment);
				newInvoicePayment.setInvoice(invoice);
				newInvoicePayment.setPayment(payment);
				invoicePayment.setPaymentAmount(invoicePaymentAmount);
			} else if (invoicePayment != null){
				invoicePaymentDao.remove(invoicePayment);
			}
		}
	}
}
