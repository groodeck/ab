package org.ab.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.ab.dao.ContractDao;
import org.ab.dao.CorrectionDao;
import org.ab.dao.InvoiceDao;
import org.ab.dao.SubscriberDao;
import org.ab.entity.Correction;
import org.ab.entity.Invoice;
import org.ab.entity.Subscriber;
import org.ab.model.CorrectionModel;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.service.converter.CorrectionConverter;
import org.ab.service.converter.InvoiceConverter;
import org.ab.service.generator.CorrectionFileGenerator;
import org.ab.service.generator.CorrectionNumberGenerator;
import org.ab.service.generator.CorrectionServiceRecord;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@Component
@Transactional
public class CorrectionService {

	@Autowired
	public ContractDao contractDao;

	@Autowired
	private InvoiceConverter invoiceConverter;

	@Autowired
	private CorrectionConverter correctionConverter;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private CorrectionDao correctionDao;

	@Autowired
	private SubscriberDao subscriberDao;

	@Autowired
	private CorrectionNumberGenerator numberGenerator;

	@Autowired
	private CorrectionFileGenerator correctionFileGenerator;

	public void calculateCorrectionSum(final CorrectionModel correction) {
		BigDecimal correctionNetAmount = BigDecimal.ZERO;
		BigDecimal correctionVatAmount = BigDecimal.ZERO;
		BigDecimal correctionGrossAmount = BigDecimal.ZERO;
		for(final CorrectionServiceRecord serviceRec : correction.getServiceRecords()){
			if(serviceRec.getNetAmount() != null){
				correctionNetAmount = correctionNetAmount.add(serviceRec.getNetAmount());
			}
			if(serviceRec.getVatAmount() != null){
				correctionVatAmount = correctionVatAmount.add(serviceRec.getVatAmount());
			}
			if(serviceRec.getGrossAmount() != null){
				correctionGrossAmount = correctionGrossAmount.add(serviceRec.getGrossAmount());
			}
		}
		final InvoiceModel invoice = correction.getInvoice();
		final BigDecimal netAmountDiff = correctionNetAmount.subtract(invoice.getNetAmount());
		final BigDecimal vatAmountDiff = correctionVatAmount.subtract(invoice.getVatAmount());
		final BigDecimal grossAmountDiff = correctionGrossAmount.subtract(invoice.getGrossAmount());
		correction.setNetAmount(correctionNetAmount);
		correction.setVatAmount(correctionVatAmount);
		correction.setGrossAmount(correctionGrossAmount);
		correction.setNetAmountDiff(netAmountDiff);
		correction.setVatAmountDiff(vatAmountDiff);
		correction.setGrossAmountDiff(grossAmountDiff);
	}

	public List<CorrectionModel> findCorrections(final List<InvoiceModel> invoices) {
		final ImmutableMap<Integer, InvoiceModel> invoiceMap = Maps.uniqueIndex(invoices,
				new Function<InvoiceModel, Integer>(){
			@Override
			public Integer apply(final InvoiceModel entry) {
				return entry.getInvoiceId();
			}
		});
		final List<org.ab.entity.Correction> corrections = correctionDao.findCorrections(invoiceMap.keySet());
		return correctionConverter.convertEntities(corrections, invoiceMap);
	}

	public String getCorrectionFile(final int correctionId) {
		final Correction correction = correctionDao.getCorrection(correctionId);
		final String correctionNumber = correction.getCorrectionNumber();
		return correctionFileGenerator.createFile(correctionNumber, correction.getCorrectionContent().getCorrectionHtml());
	}

	public String getCorrectionHtmlContent(final int correctionId) {
		final org.ab.entity.Correction correction = correctionDao.getCorrection(correctionId);
		return correction.getCorrectionContent().getCorrectionHtml();
	}

	private String getCorrectionNumber() {
		final LocalDate today = LocalDate.now();
		final LocalDate dateFrom = today.dayOfMonth().withMinimumValue();
		final LocalDate dateTo = today.dayOfMonth().withMaximumValue();
		return numberGenerator.generate(dateFrom, dateTo);
	}

	private LocalDate getFirstOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMinimumValue();
	}

	private LocalDate getLastOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMaximumValue();
	}

	private LocalDate getLocalDate(final InvoiceGenerationParams generationParams) {
		return LocalDate.now()
				.withYear(Integer.parseInt(generationParams.getYear()))
				.withMonthOfYear(Integer.parseInt(generationParams.getMonth()));
	}

	private void persist(final List<InvoiceModel> invoices) {
		for(final InvoiceModel invoice : invoices){
			final org.ab.entity.Invoice entity = invoiceConverter.convert(invoice);
			final Integer invoiceId = invoiceDao.save(entity);
			invoice.setInvoiceId(invoiceId);
		}
	}

	public CorrectionModel prepareCorrection(final int invoiceId) {
		final Invoice invoice = invoiceDao.getInvoice(invoiceId);
		final InvoiceModel invoiceModel = invoiceConverter.convertEntity(invoice);
		final String correctionNumber = getCorrectionNumber();

		return new CorrectionModel.Builder()
		.fromInvoice(invoiceModel)
		.withCorrectionNumber(correctionNumber)
		.build();
	}

	private void printFile(final String file){
		try {
			final FileInputStream fis = new FileInputStream(file);
			final DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			final Doc pdfDoc = new SimpleDoc(fis, flavor, null);
			final PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
			final DocPrintJob printJob = printService.createPrintJob();
			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
			fis.close();
		} catch (final PrintException | IOException e) {
			e.printStackTrace();
		}
	}

	private void printToPrinter(final List<String> filesToPrint) {
		for(final String file : filesToPrint){
			printFile(file);
		}
	}

	public void save(final CorrectionModel correction) {
		final Correction entity = correctionConverter.convert(correction);
		correctionDao.save(entity);
		updateSubscriberBalance(correction);
		final Optional<String> fileToPrint = correctionFileGenerator.generatePdf(correction);
		// TODO:
		//	1. uncomment in production
		// 	2. print only invoices for specific subscriber - email not defined;
		//printToPrinter(filesToPrint);
	}

	private void updateSubscriberBalance(final CorrectionModel correction) {
		//		final Integer invoiceId = correction.getInvoice().getInvoiceId();
		//		final Invoice invoice = invoiceDao.getInvoice(invoiceId);
		//		final Subscriber subscriber = invoice.getContract().getSubscriber();

		final String subscriberIdn = correction.getInvoice().getSubscriberIdn();
		final Optional<Subscriber> subscriber = subscriberDao.findByIdn(subscriberIdn);
		if(subscriber.isPresent()){
			subscriber.get().subtractBalanceAmount(correction.getGrossAmountDiff());
		}
	}

}
