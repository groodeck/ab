package org.ab.service;

import java.util.List;

import org.ab.dao.ContractDao;
import org.ab.entity.Contract;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
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

	@Transactional
	public List<Invoice> generateInvoices(final InvoiceGenerationParams generationParams) {
		final LocalDate dateFrom = getFirstOfMonth(generationParams);
		final LocalDate dateTo = getLastOfMonth(generationParams);
		final List<Contract> contracts = this.contractDao.findContracts(dateFrom, dateTo);
		return this.invoicesGenerator.generateInvoices(contracts);

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
