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
import org.ab.entity.Invoice;
import org.ab.model.CorrectionModel;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.service.converter.InvoiceConverter;
import org.ab.service.generator.CorrectionNumberGenerator;
import org.ab.service.generator.CorrectionServiceRecord;
import org.ab.service.generator.InvoiceFileGenerator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CorrectionService {

	@Autowired
	public ContractDao contractDao;

	@Autowired
	private InvoiceConverter invoiceConverter;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private CorrectionDao correctionDao;

	@Autowired
	private CorrectionNumberGenerator numberGenerator;

	@Autowired
	private InvoiceFileGenerator invoiceFileGenerator;

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

	public List<InvoiceModel> findInvoices(final String subscriberIdn, final LocalDate dateFrom, final LocalDate dateTo) {
		final List<org.ab.entity.Invoice> invoices = invoiceDao.findInvoices(subscriberIdn, dateFrom, dateTo);
		return invoiceConverter.convertEntities(invoices);
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
		final String correctionNumber = numberGenerator.generate(invoiceModel);

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

}
