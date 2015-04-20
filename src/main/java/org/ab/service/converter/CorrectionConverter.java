package org.ab.service.converter;

import java.util.List;

import org.ab.dao.ContractPackageDao;
import org.ab.dao.InvoiceDao;
import org.ab.dao.UserDao;
import org.ab.entity.CorrectionContent;
import org.ab.entity.CorrectionRecord;
import org.ab.entity.Invoice;
import org.ab.entity.Subscriber;
import org.ab.model.CorrectionModel;
import org.ab.model.InvoiceModel;
import org.ab.service.generator.CorrectionContentGenerator;
import org.ab.service.generator.CorrectionServiceRecord;
import org.ab.service.generator.InvoiceParticipant;
import org.ab.service.generator.InvoicesGenerator;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;

@Component
public class CorrectionConverter {

	private final class ToModelCorrectionFunction implements Function<org.ab.entity.Correction, CorrectionModel>{

		private final ImmutableMap<Integer, InvoiceModel> invoiceMap;
		private final boolean convertRecords;

		public ToModelCorrectionFunction(final ImmutableMap<Integer, InvoiceModel> invoiceMap, final boolean convertRecords) {
			this.invoiceMap = invoiceMap;
			this.convertRecords = convertRecords;
		}

		@Override
		public CorrectionModel apply(final org.ab.entity.Correction input) {
			final InvoiceModel invoiceModel = invoiceMap.get(input.getInvoice().getInvoiceId());
			invoiceModel.setCorrected(true);
			final CorrectionModel.Builder builder = new CorrectionModel.Builder()
			.withInvoice(invoiceModel)
			.withCorrectionId(input.getCorrectionId())
			.withCorrectionNumber(input.getCorrectionNumber())
			.withCreateDate(input.getCreateDate())
			.withReceiveDate(input.getReceiveDate())
			.withPaymentDate(input.getPaymentDate())
			.withNetAmount(input.getNetAmount())
			.withVatAmount(input.getVatAmount())
			.withGrossAmount(input.getGrossAmount())
			.withNetAmountDiff(input.getNetAmountDiff())
			.withVatAmountDiff(input.getVatAmountDiff())
			.withGrossAmountDiff(input.getGrossAmountDiff())
			.withGrossAmountDiffWords(input.getGrossAmountWords());
			if(convertRecords){
				builder.withServiceRecords(convertRecords(input.getCorrectionRecords()));
			}
			return builder.build();
		}

		private List<CorrectionServiceRecord> convertRecords(
				final List<CorrectionRecord> invoiceRecords) {
			return Lists.newArrayList(FluentIterable.from(invoiceRecords).transform(
					new Function<CorrectionRecord, CorrectionServiceRecord>(){

						@Override
						public CorrectionServiceRecord apply(final CorrectionRecord input) {
							return new CorrectionServiceRecord.Builder()
							.withServiceName(input.getServiceName())
							.withQuantity(input.getQuantity())
							.withNetPrice(input.getNetAmount())
							.withNetAmount(input.getNetAmount())
							.withVatRate(input.getVatRate())
							.withVatAmount(input.getVatAmount())
							.withGrossAmount(input.getGrossAmount())
							.withNetPriceDiff(input.getNetPriceDiff())
							.withNetAmountDiff(input.getNetAmountDiff())
							.withVatAmountDiff(input.getVatAmountDiff())
							.withGrossAmountDiff(input.getGrossAmountDiff())
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
	}

	@Autowired
	private ContractPackageDao packageDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private DeviceConverter deviceConverter;

	@Autowired
	private InvoicesGenerator ivoicesGenerator;

	@Autowired
	private CorrectionContentGenerator contentGenerator;

	private final Function<CorrectionModel, org.ab.entity.Correction> toEntityCorrection =
			new Function<CorrectionModel, org.ab.entity.Correction>(){

		@Override
		public org.ab.entity.Correction apply(final CorrectionModel input) {
			final Invoice invoice = invoiceDao.getInvoice(input.getInvoice().getInvoiceId());
			final org.ab.entity.Correction entity = new org.ab.entity.Correction();
			entity.setCorrectionId(input.getCorrectionId());
			entity.setInvoice(invoice);
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
			entity.setGrossAmountWords(input.getGrossAmountDiffWords());
			entity.setPaymentDate(input.getPaymentDate());
			final CorrectionContent correctionContent = new CorrectionContent();
			final String htmlContent = contentGenerator.generateHtml(input);
			correctionContent.setCorrectionHtml(htmlContent);
			entity.setCorrectionContent(correctionContent);
			correctionContent.setCorrection(entity);
			input.setHtmlContent(htmlContent);
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

	public org.ab.entity.Correction convert(final CorrectionModel invoice) {
		return toEntityCorrection.apply(invoice);
	}

	public List<CorrectionModel> convertEntities(final List<org.ab.entity.Correction> corrections,
			final ImmutableMap<Integer,InvoiceModel> invoiceMap) {
		final ToModelCorrectionFunction toModelCorrection = new ToModelCorrectionFunction(invoiceMap, false);
		return FluentIterable.from(corrections).transform(toModelCorrection).toList();
	}

	//	public CorrectionModel convertEntity(final org.ab.entity.Correction invoice) {
	//		return toModelCorrection.apply(invoice);
	//	}
}
