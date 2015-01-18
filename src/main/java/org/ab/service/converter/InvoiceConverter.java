package org.ab.service.converter;

import java.util.List;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.UserDao;
import org.ab.entity.InvoiceContent;
import org.ab.entity.InvoiceRecord;
import org.ab.entity.Subscriber;
import org.ab.model.InvoiceModel;
import org.ab.service.generator.Invoice;
import org.ab.service.generator.InvoiceParticipant;
import org.ab.service.generator.InvoiceServiceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Component
public class InvoiceConverter {

	@Autowired
	private ContractPackageDao packageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DeviceConverter deviceConverter;

	private final Function<Invoice, org.ab.entity.Invoice> toEntityInvoice =
			new Function<Invoice, org.ab.entity.Invoice>(){

		@Override
		public org.ab.entity.Invoice apply(final Invoice input) {
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
			entity.setPaid(input.isPaid());
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

	private final Function<org.ab.entity.Invoice, InvoiceModel> toModelInvoice =
			new Function<org.ab.entity.Invoice, InvoiceModel>(){

		@Override
		public InvoiceModel apply(final org.ab.entity.Invoice input) {
			final InvoiceModel model = new InvoiceModel.Builder()
			.withInvoiceId(input.getInvoiceId().toString())
			.withInvoiceNumber(input.getInvoiceNumber())
			.withSubscriber(convertSubscriber(input.getContract().getSubscriber()))
			.withCreateDate(input.getCreateDate().toString())
			.withReceiveDate(input.getReceiveDate().toString())
			.withSettlementPeriodStart(input.getSettlementPeriodStart().toString())
			.withSettlementPeriodEnd(input.getSettlementPeriodEnd().toString())
			.withNetAmount(input.getNetAmount().toPlainString())
			.withVatAmount(input.getVatAmount().toPlainString())
			.withGrossAmount(input.getGrossAmount().toPlainString())
			.withPaid(input.isPaid())
			.withPaymentDate(input.getPaymentDate().toString())
			.withHtmlContent(input.getInvoiceContent().getInvoiceHtml())
			.build();
			return model;
		}

		private InvoiceParticipant convertSubscriber(final Subscriber subscriber) {
			return new InvoiceParticipant.Builder()
			.withName(subscriber.getEffectiveName())
			.build();
		}
	};

	public List<org.ab.entity.Invoice> convert(final List<Invoice> invoices) {
		return FluentIterable.from(invoices).transform(toEntityInvoice).toList();
	}

	public List<InvoiceModel> convertEntities(final List<org.ab.entity.Invoice> invoices) {
		return FluentIterable.from(invoices).transform(toModelInvoice).toList();
	}
}
