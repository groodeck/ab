package org.ab.service.converter;

import java.math.BigDecimal;
import java.util.List;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.UserDao;
import org.ab.entity.Invoice;
import org.ab.entity.InvoiceContent;
import org.ab.entity.InvoicePayment;
import org.ab.entity.InvoiceRecord;
import org.ab.entity.Payment;
import org.ab.entity.Subscriber;
import org.ab.model.InvoiceModel;
import org.ab.model.InvoicePaymentModel;
import org.ab.model.PaymentModel;
import org.ab.model.SubscriberModel;
import org.ab.service.generator.InvoiceServiceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

@Component
public class PaymentConverter {

	@Autowired
	private ContractPackageDao packageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DeviceConverter deviceConverter;

	@Autowired
	private SubscriberConverter subscriberConverter;

	private final Function<InvoiceModel, org.ab.entity.Invoice> toEntityInvoice =
			new Function<InvoiceModel, org.ab.entity.Invoice>(){

		@Override
		public org.ab.entity.Invoice apply(final InvoiceModel input) {
			final org.ab.entity.Invoice entity = new org.ab.entity.Invoice();
			entity.setInvoiceId(input.getInvoiceId());
			entity.setContract(input.getContract());
			entity.setInvoiceNumber(input.getInvoiceNumber());
			entity.setCreateDate(input.getCreateDate());
			entity.setReceiveDate(input.getReceiveDate());
			entity.setSettlementPeriodStart(input.getSettlementPeriodStart());
			entity.setSettlementPeriodEnd(input.getSettlementPeriodEnd());
			entity.setInvoiceRecords(convertRecords(input.getServiceRecords()));
			entity.setNetAmount(input.getNetAmount());
			entity.setVatAmount(input.getVatAmount());
			entity.setGrossAmount(input.getGrossAmount());
			entity.setGrossAmountWords(input.getGrossAmountWords());
			entity.setPaidAmount(input.getPaidAmount());
			entity.setPaymentDate(input.getPaymentDate());
			final InvoiceContent invoiceContent = new InvoiceContent();
			invoiceContent.setInvoiceHtml(input.getHtmlContent());
			entity.setInvoiceContent(invoiceContent);
			invoiceContent.setInvoice(entity);
			return entity;
		}

		private List<InvoiceRecord> convertRecords(
				final List<InvoiceServiceRecord> serviceRecords) {
			return FluentIterable.from(serviceRecords).transform(
					new Function<InvoiceServiceRecord, InvoiceRecord>(){

						@Override
						public InvoiceRecord apply(final InvoiceServiceRecord input) {
							final InvoiceRecord record = new InvoiceRecord();
							record.setServiceName(input.getServiceName());
							record.setQuantity(input.getQuantity());
							record.setNetPrice(input.getNetAmount());
							record.setNetAmount(input.getNetAmount());
							record.setVatRate(input.getVatRate());
							record.setVatAmount(input.getVatAmount());
							record.setGrossAmount(input.getGrossAmount());
							return record;
						}
					}).toList();
		}
	};

	private final Function<org.ab.entity.InvoicePayment, InvoicePaymentModel> toModelInvoicePayment =
			new Function<org.ab.entity.InvoicePayment, InvoicePaymentModel>(){

		@Override
		public InvoicePaymentModel apply(final org.ab.entity.InvoicePayment input) {
			final InvoicePaymentModel model = new InvoicePaymentModel();
			final Invoice invoice = input.getInvoice();
			model.setInvoiceId(invoice.getInvoiceId().toString());
			model.setSettlementPeriod(String.format(
					"%s - %s", invoice.getSettlementPeriodStart(), invoice.getSettlementPeriodEnd()));
			model.setInvoiceNumber(invoice.getInvoiceNumber());
			final BigDecimal grossAmount = invoice.getGrossAmount();
			model.setInvoiceGrossAmount(grossAmount.toPlainString());
			final BigDecimal paymentAmount = input.getPaymentAmount();
			model.setPaymentAmount(paymentAmount.toPlainString());
			model.setShouldBePaid(true);
			model.setInvoiceLeftToPay(sumPayments(invoice.getInvoicePayments()));
			return model;
		}
	};

	private final Function<org.ab.entity.Invoice, InvoicePaymentModel> toModelInvoice =
			new Function<org.ab.entity.Invoice, InvoicePaymentModel>(){

		@Override
		public InvoicePaymentModel apply(final org.ab.entity.Invoice input) {
			final InvoicePaymentModel model = new InvoicePaymentModel();
			model.setInvoiceId(input.getInvoiceId().toString());
			model.setSettlementPeriod(String.format(
					"%s - %s", input.getSettlementPeriodStart(), input.getSettlementPeriodEnd()));
			model.setInvoiceNumber(input.getInvoiceNumber());
			final BigDecimal grossAmount = input.getGrossAmount();
			model.setInvoiceGrossAmount(grossAmount.toPlainString());
			model.setInvoiceLeftToPay(sumPayments(input.getInvoicePayments()));
			return model;
		}
	};

	private final Function<org.ab.entity.Payment, PaymentModel> toModelPayment =
			new Function<org.ab.entity.Payment, PaymentModel>(){

		@Override
		public PaymentModel apply(final org.ab.entity.Payment input) {
			final PaymentModel model = new PaymentModel();
			model.setPaymentId(input.getPaymentId().toString());
			model.setPaymentAmount(input.getPaymentAmount().toPlainString());
			model.setCreateDate(input.getCreateDate().toString());
			final Subscriber subscriber = Iterables.getFirst(input.getInvoicePayments(), null)
					.getInvoice().getContract().getSubscriber();
			final SubscriberModel subscriberModel = convertSubscriber(subscriber);
			model.setSubscriber(subscriberModel);
			final List<InvoicePaymentModel> invoicePayments =
					FluentIterable.from(input.getInvoicePayments()).transform(toModelInvoicePayment).toList();
			model.setInvoices(invoicePayments);
			return model;
		}

		private SubscriberModel convertSubscriber(final Subscriber subscriber) {
			final SubscriberModel subscriberModel = new SubscriberModel();
			subscriberModel.setSubscriberId(subscriber.getSubscriberId().toString());
			subscriberModel.setSubscriberIdn(subscriber.getSubscriberIdn());
			subscriberModel.setName(subscriber.getName());
			subscriberModel.setSurname(subscriber.getSurname());
			subscriberModel.setCompanyName(subscriber.getCompanyName());
			return subscriberModel;
		}
	};

	public org.ab.entity.Invoice convert(final InvoiceModel invoice) {
		return toEntityInvoice.apply(invoice);
	}

	public List<InvoicePaymentModel> convertInvoiceEntities(final List<org.ab.entity.Invoice> invoices) {
		return FluentIterable.from(invoices).transform(toModelInvoice).toList();
	}

	public List<InvoicePaymentModel> convertInvoicePaymentEntities(final List<org.ab.entity.InvoicePayment> invoices) {
		return FluentIterable.from(invoices).transform(toModelInvoicePayment).toList();
	}

	public List<PaymentModel> convertPaymentEntities(final List<Payment> invoices) {
		return FluentIterable.from(invoices).transform(toModelPayment).toList();
	}

	public PaymentModel convertPaymentEntity(final Payment invoice) {
		return toModelPayment.apply(invoice);
	}

	private String sumPayments(final List<InvoicePayment> invoicePayments) {
		BigDecimal sum = BigDecimal.ZERO;
		for(final InvoicePayment payment : invoicePayments){
			sum = sum.add(payment.getPaymentAmount());
		}
		return sum.toPlainString();
	}
}
