package org.ab.service;

import java.math.BigDecimal;
import java.util.List;

import org.ab.dao.InvoiceDao;
import org.ab.dao.InvoicePaymentDao;
import org.ab.dao.PaymentDao;
import org.ab.entity.Invoice;
import org.ab.entity.InvoicePayment;
import org.ab.entity.Payment;
import org.ab.model.InvoicePaymentModel;
import org.ab.model.PaymentModel;
import org.ab.service.converter.PaymentConverter;
import org.ab.util.Translator;
import org.apache.commons.lang.StringUtils;
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

	private void createInvoicePayment(final Payment payment, final InvoicePaymentModel invoiceModel) {
		if(invoiceModel.isShouldBePaid()){
			final Invoice invoice = invoiceDao.getInvoice(Integer.parseInt(invoiceModel.getInvoiceId()));
			final BigDecimal invoicePaymentAmount = Translator.toAmount(invoiceModel.getPaymentAmount());
			final InvoicePayment invoicePayment = new InvoicePayment();
			invoicePayment.setInvoice(invoice);
			invoicePayment.setPayment(payment);
			invoicePayment.setPaymentAmount(invoicePaymentAmount);
			invoicePaymentDao.save(invoicePayment);
		}
	}

	public List<PaymentModel> findPayments(final String subscriberIdn, final LocalDate dateFrom, final LocalDate dateTo) {
		final List<Payment> payments = paymentDao.findPayments(subscriberIdn, dateFrom, dateTo);
		return paymentConverter.convertPaymentEntities(payments);
	}

	private InvoicePayment getInvoicePayment(final InvoicePaymentModel invoiceModel) {
		final String paymentId = invoiceModel.getPaymentId();
		final String invoiceId = invoiceModel.getInvoiceId();
		if(invoiceId != null && paymentId != null){
			return invoicePaymentDao.getByInvoiceAndPayment(Integer.getInteger(invoiceId), Integer.getInteger(paymentId));
		} else {
			return null;
		}
	}

	private Payment getOrCreatePayment(final String paymentId) {
		if(StringUtils.isBlank(paymentId)){
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

	public void save(final PaymentModel paymentModel, final String name) {
		final Payment payment = getOrCreatePayment(paymentModel.getPaymentId());
		payment.setCreateDate(Translator.toLocalDate(paymentModel.getCreateDate()));
		payment.setPaymentAmount(Translator.toAmount(paymentModel.getPaymentAmount()));
		saveInvoicePayments(payment, paymentModel.getInvoices());
		paymentDao.save(payment);
	}

	private void saveInvoicePayments(final Payment payment, final List<InvoicePaymentModel> invoicesModel) {
		for(final InvoicePaymentModel invoiceModel : invoicesModel){
			final InvoicePayment invoicePayment = getInvoicePayment(invoiceModel);
			if(invoicePayment == null){
				createInvoicePayment(payment, invoiceModel);
			} else {
				update(invoicePayment, invoiceModel);
			}
		}
	}

	private void update(final InvoicePayment invoicePayment, final InvoicePaymentModel invoiceModel) {
		if(invoiceModel.isShouldBePaid()){
			final BigDecimal invoicePaymentAmount = Translator.toAmount(invoiceModel.getPaymentAmount());
			invoicePayment.setPaymentAmount(invoicePaymentAmount);
			invoicePaymentDao.save(invoicePayment);
		} else if (invoicePayment != null){
			invoicePaymentDao.remove(invoicePayment);
		}
	}
}
