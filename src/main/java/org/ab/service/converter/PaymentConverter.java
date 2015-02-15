package org.ab.service.converter;

import java.math.BigDecimal;
import java.util.List;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.SubscriberDao;
import org.ab.dao.UserDao;
import org.ab.entity.Invoice;
import org.ab.entity.InvoicePayment;
import org.ab.entity.Payment;
import org.ab.entity.Subscriber;
import org.ab.model.InvoicePaymentModel;
import org.ab.model.PaymentModel;
import org.ab.model.SubscriberModel;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Component
public class PaymentConverter {

	@Autowired
	private ContractPackageDao packageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SubscriberDao subscriberDao;

	@Autowired
	private DeviceConverter deviceConverter;

	@Autowired
	private SubscriberConverter subscriberConverter;

	private final Function<org.ab.entity.InvoicePayment, InvoicePaymentModel> toModelInvoicePayment =
			new Function<org.ab.entity.InvoicePayment, InvoicePaymentModel>(){

		@Override
		public InvoicePaymentModel apply(final org.ab.entity.InvoicePayment input) {
			final InvoicePaymentModel model = new InvoicePaymentModel();
			final Invoice invoice = input.getInvoice();
			model.setInvoiceId(invoice.getInvoiceId().toString());
			model.setPaymentId(input.getPayment().getPaymentId().toString());
			model.setSettlementPeriod(String.format(
					"%s - %s", invoice.getSettlementPeriodStart(), invoice.getSettlementPeriodEnd()));
			model.setInvoiceNumber(invoice.getInvoiceNumber());
			final BigDecimal grossAmount = invoice.getGrossAmount();
			model.setInvoiceGrossAmount(grossAmount.toPlainString());
			final BigDecimal paymentAmount = input.getPaymentAmount();
			model.setPaymentAmount(paymentAmount.toPlainString());
			model.setShouldBePaid(true);
			final BigDecimal paid = sumPayments(invoice.getInvoicePayments());
			model.setInvoiceLeftToPay(grossAmount.subtract(paid).toPlainString());
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
			final BigDecimal paid = sumPayments(input.getInvoicePayments());
			model.setInvoiceLeftToPay(grossAmount.subtract(paid).toPlainString());
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
			final Subscriber subscriber = subscriberDao.getSubscriber(input.getSubscriberId());
			final SubscriberModel subscriberModel = convertSubscriber(subscriber);
			model.setSubscriber(subscriberModel);
			final List<InvoicePaymentModel> invoicePayments =
					Lists.newArrayList(FluentIterable.from(input.getInvoicePayments()).transform(toModelInvoicePayment));
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

	private BigDecimal sumPayments(final List<InvoicePayment> invoicePayments) {
		BigDecimal sum = BigDecimal.ZERO;
		for(final InvoicePayment payment : invoicePayments){
			sum = sum.add(payment.getPaymentAmount());
		}
		return sum;
	}
}
