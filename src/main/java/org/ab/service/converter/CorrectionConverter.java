package org.ab.service.converter;

import java.util.List;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.UserDao;
import org.ab.entity.CorrectionRecord;
import org.ab.entity.InvoiceRecord;
import org.ab.entity.Subscriber;
import org.ab.model.CorrectionModel;
import org.ab.model.InvoiceModel;
import org.ab.service.generator.CorrectionServiceRecord;
import org.ab.service.generator.InvoiceParticipant;
import org.ab.service.generator.InvoiceServiceRecord;
import org.ab.service.generator.InvoicesGenerator;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

@Component
public class CorrectionConverter {

	@Autowired
	private ContractPackageDao packageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private DeviceConverter deviceConverter;

	@Autowired
	private InvoicesGenerator ivoicesGenerator;

	private final Function<CorrectionModel, org.ab.entity.Correction> toEntityCorrection =
			new Function<CorrectionModel, org.ab.entity.Correction>(){

		@Override
		public org.ab.entity.Correction apply(final CorrectionModel input) {
			final org.ab.entity.Correction entity = new org.ab.entity.Correction();
			entity.setCorrectionId(input.getCorrectionId());
			entity.setInvoiceId(input.getInvoice().getInvoiceId());
			entity.setCorrectionNumber(input.getCorrectionNumber());
			entity.setCreateDate(input.getCreateDate());
			entity.setReceiveDate(input.getReceiveDate());
			entity.setCorrectionRecords(convertRecords(input.getServiceRecords()));
			entity.setNetAmount(input.getNetAmount());
			entity.setNetAmountDiff(input.getNetAmountDiff());
			entity.setVatAmount(input.getVatAmount());
			entity.setVatAmountDiff(input.getVatAmountDiff());
			entity.setGrossAmount(input.getGrossAmount());
			entity.setGrossAmountDiff(input.getGrossAmountDiff());
			entity.setGrossAmountWords(input.getGrossAmountWords());
			entity.setPaymentDate(input.getPaymentDate());
			//			final InvoiceContent invoiceContent = new InvoiceContent();
			//			invoiceContent.setInvoiceHtml(input.getHtmlContent());
			//			entity.setInvoiceContent(invoiceContent);
			//			invoiceContent.setInvoice(entity);
			return entity;
		}

		private List<CorrectionRecord> convertRecords(
				final List<CorrectionServiceRecord> serviceRecords) {
			return FluentIterable.from(serviceRecords).transform(
					new Function<CorrectionServiceRecord, CorrectionRecord>(){

						@Override
						public CorrectionRecord apply(final CorrectionServiceRecord input) {
							final CorrectionRecord entity = new CorrectionRecord();
							entity.setCorrectionRecordId(input.getCorrectionRecordId());
							entity.setInvoiceRecordId(input.getInvoiceRecord().getInvoiceRecordId());
							entity.setServiceName(input.getServiceName());
							entity.setQuantity(input.getQuantity());
							entity.setQuantityDiff(input.getQuantityDiff());
							entity.setNetPrice(input.getNetPrice());
							entity.setNetPriceDiff(input.getNetPriceDiff());
							entity.setNetAmount(input.getNetAmount());
							entity.setNetAmountDiff(input.getNetAmountDiff());
							entity.setVatRate(input.getVatRate());
							entity.setVatAmount(input.getVatAmount());
							entity.setVatAmountDiff(input.getVatAmountDiff());
							entity.setGrossAmount(input.getGrossAmount());
							entity.setGrossAmountDiff(input.getGrossAmountDiff());
							return entity;
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

	public org.ab.entity.Correction convert(final CorrectionModel invoice) {
		return toEntityCorrection.apply(invoice);
	}

	public List<InvoiceModel> convertEntities(final List<org.ab.entity.Invoice> invoices) {
		return FluentIterable.from(invoices).transform(toModelInvoice).toList();
	}

	public InvoiceModel convertEntity(final org.ab.entity.Invoice invoice) {
		return toModelInvoice.apply(invoice);
	}
}
