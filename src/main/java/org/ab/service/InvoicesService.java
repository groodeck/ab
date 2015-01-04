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
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.service.converter.InvoiceConverter;
import org.ab.service.generator.Invoice;
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

	public List<InvoiceModel> findInvoices(final LocalDate dateFrom, final LocalDate dateTo) {
		final List<org.ab.entity.Invoice> invoices = invoiceDao.findAll();
		return invoiceConverter.convertEntities(invoices);
	}

	@Transactional
	public List<Invoice> generateInvoices(final InvoiceGenerationParams generationParams) {
		final LocalDate dateFrom = getFirstOfMonth(generationParams);
		final LocalDate dateTo = getLastOfMonth(generationParams);
		final List<Contract> contracts = contractDao.findContracts(dateFrom, dateTo);
		final List<Invoice> invoices = invoicesGenerator.generateInvoices(contracts, dateFrom, dateTo);
		if(!CollectionUtils.isEmpty(invoices)){
			persist(invoices);
			final List<String> filesToPrint = invoiceFileGenerator.generatePdf(invoices);
			printToPrinter(filesToPrint);
		}
		return invoices;

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

	private void persist(final List<Invoice> invoices) {
		final List<org.ab.entity.Invoice> entities = invoiceConverter.convert(invoices);
		invoiceDao.save(entities);
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
