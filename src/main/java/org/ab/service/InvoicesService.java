package org.ab.service;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.ab.dao.InvoiceDao;
import org.ab.entity.Contract;
import org.ab.entity.Invoice;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.service.converter.InvoiceConverter;
import org.ab.service.generator.InvoiceFileGenerator;
import org.ab.service.generator.InvoicesGenerator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Component
@Transactional
public class InvoicesService {

	@Autowired
	public ContractDao contractDao;

	@Autowired
	private InvoiceConverter invoiceConverter;

	@Autowired
	private InvoiceDao invoiceDao;

	@Autowired
	private InvoicesGenerator invoicesGenerator;

	@Autowired
	private InvoiceFileGenerator invoiceFileGenerator;

	public List<InvoiceModel> findInvoices(final String subscriberIdn, final LocalDate dateFrom, final LocalDate dateTo) {
		final List<org.ab.entity.Invoice> invoices = invoiceDao.findInvoices(subscriberIdn, dateFrom, dateTo);
		return invoiceConverter.convertEntities(invoices);
	}

	@Transactional
	public List<InvoiceModel> generateInvoices(final InvoiceGenerationParams generationParams) {
		final LocalDate dateFrom = getFirstOfMonth(generationParams);
		final LocalDate dateTo = getLastOfMonth(generationParams);
		final List<Contract> contracts = contractDao.findContractsToInvoiceGenerate(dateFrom, dateTo);
		final List<InvoiceModel> invoices = invoicesGenerator.generateInvoices(contracts, dateFrom, dateTo);
		if(!CollectionUtils.isEmpty(invoices)){
			persist(invoices);
			//			final List<String> filesToPrint = invoiceFileGenerator.generatePdf(invoices);
			// TODO:
			//	1. uncomment in production
			// 	2. print only invoices for specific subscriber - email not defined;
			//printToPrinter(filesToPrint);
		}
		return invoices;

	}

	private LocalDate getFirstOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMinimumValue();
	}

	public String getInvoiceFile(final int invoiceId) {
		final Invoice invoice = invoiceDao.getInvoice(invoiceId);
		final String invoiceNumber = invoice.getInvoiceNumber();
		return invoiceFileGenerator.createFile(invoiceNumber, invoice.getInvoiceContent().getInvoiceHtml());
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
