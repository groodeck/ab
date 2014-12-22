package org.ab.service;

import java.util.List;

import org.ab.dao.ContractDao;
import org.ab.dao.InvoiceDao;
import org.ab.entity.Contract;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.service.converter.InvoiceConverter;
import org.ab.service.generator.Invoice;
import org.ab.service.generator.InvoicesGenerator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InvoicesService {

	@Autowired
	public ContractDao contractDao;

	@Autowired
	private InvoicesGenerator invoicesGenerator;

	@Autowired
	private InvoiceConverter invoiceConverter;

	@Autowired
	private InvoiceDao invoiceDao;

	@Transactional
	public List<Invoice> generateInvoices(final InvoiceGenerationParams generationParams) {
		final LocalDate dateFrom = getFirstOfMonth(generationParams);
		final LocalDate dateTo = getLastOfMonth(generationParams);
		final List<Contract> contracts = this.contractDao.findContracts(dateFrom, dateTo);
		final List<Invoice> invoices = this.invoicesGenerator.generateInvoices(contracts, dateFrom, dateTo);
		persist(invoices);
		return invoices;

	}

	private void persist(final List<Invoice> invoices) {
		final List<org.ab.entity.Invoice> entities = this.invoiceConverter.convert(invoices);
		this.invoiceDao.save(entities);
	}

	private LocalDate getLastOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMaximumValue();
	}

	private LocalDate getLocalDate(final InvoiceGenerationParams generationParams) {
		return LocalDate.now()
			.withYear(Integer.parseInt(generationParams.getYear()))
			.withMonthOfYear(Integer.parseInt(generationParams.getMonth()));
	}

	private LocalDate getFirstOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMinimumValue();
	}

	public List<InvoiceModel> findInvoices(final LocalDate dateFrom, final LocalDate dateTo) {
		// TODO Auto-generated method stub
		return null;
	}
}
