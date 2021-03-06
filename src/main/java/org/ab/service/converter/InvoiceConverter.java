package org.ab.service.converter;

import java.util.List;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.UserDao;
import org.ab.entity.InvoiceContent;
import org.ab.entity.InvoiceRecord;
import org.ab.entity.Subscriber;
import org.ab.model.InvoiceModel;
import org.ab.service.generator.InvoiceParticipant;
import org.ab.service.generator.InvoiceServiceRecord;
import org.ab.service.generator.InvoicesGenerator;
import org.assertj.core.util.Lists;
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

	@Autowired
	private InvoicesGenerator ivoicesGenerator;

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
			final Subscriber subscriber = input.getContract().getSubscriber();
			final InvoiceModel model = new InvoiceModel.Builder()
			.withInvoiceId(input.getInvoiceId())
			.withInvoiceNumber(input.getInvoiceNumber())
			.withSubscriber(convertSubscriber(subscriber))
			.withSubscriberIdn(subscriber.getSubscriberIdn())
			.withSeller(getSeller())
			.withCreateDate(input.getCreateDate())
			.withReceiveDate(input.getReceiveDate())
			.withSettlementPeriodStart(input.getSettlementPeriodStart())
			.withSettlementPeriodEnd(input.getSettlementPeriodEnd())
			.withNetAmount(input.getNetAmount())
			.withVatAmount(input.getVatAmount())
			.withGrossAmount(input.getGrossAmount())
			.withPaymentDate(input.getPaymentDate())
			.withServiceRecords(convertRecords(input.getInvoiceRecords()))
			.build();
			return model;
		}

		private List<InvoiceServiceRecord> convertRecords(
				final List<InvoiceRecord> invoiceRecords) {
			return Lists.newArrayList(FluentIterable.from(invoiceRecords).transform(
					new Function<InvoiceRecord, InvoiceServiceRecord>(){

						@Override
						public InvoiceServiceRecord apply(final InvoiceRecord input) {
							return new InvoiceServiceRecord.Builder()
							.withInvoiceRecordId(input.getInvoiceRecordId())
							.withServiceName(input.getServiceName())
							.withQuantity(input.getQuantity())
							.withNetPrice(input.getNetAmount())
							.withNetAmount(input.getNetAmount())
							.withVatRate(input.getVatRate())
							.withVatAmount(input.getVatAmount())
							.withGrossAmount(input.getGrossAmount())
							.build();
						}
					}).toList());
		}

		private InvoiceParticipant convertSubscriber(final Subscriber subscriber) {
			return ivoicesGenerator.getInvoiceParticipant(subscriber);
		}

		private InvoiceParticipant getSeller() {
			return ivoicesGenerator.getSeller();
		}
	};

	public org.ab.entity.Invoice convert(final InvoiceModel invoice) {
		return toEntityInvoice.apply(invoice);
	}

	public List<InvoiceModel> convertEntities(final List<org.ab.entity.Invoice> invoices) {
		return FluentIterable.from(invoices).transform(toModelInvoice).toList();
	}

	public InvoiceModel convertEntity(final org.ab.entity.Invoice invoice) {
		return toModelInvoice.apply(invoice);
	}
}
