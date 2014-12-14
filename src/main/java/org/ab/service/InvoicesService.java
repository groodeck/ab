package org.ab.service;

import java.util.List;

import org.ab.dao.ContractDao;
import org.ab.dao.SubscribersDao;
import org.ab.entity.Contract;
import org.ab.entity.ContractPackage;
import org.ab.entity.Service;
import org.ab.entity.Subscriber;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.model.SubscriberModel;
import org.ab.service.converter.SubscriberConverter;
import org.ab.service.generator.Invoice;
import org.ab.service.generator.InvoicesGenerator;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
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
	public void generateInvoices(final InvoiceGenerationParams generationParams) {
		// TODO Auto-generated method stub
		final LocalDate dateFrom = getFirstOfMonth(generationParams);
		final LocalDate dateTo = getLastOfMonth(generationParams);
		final List<Contract> contracts = contractDao.findContracts(dateFrom, dateTo);
		invoicesGenerator.generateInvoices(contracts);
		
	}

	private LocalDate getLastOfMonth(final InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMaximumValue();
	}

	private LocalDate getLocalDate(final InvoiceGenerationParams generationParams) {
		return LocalDate.now()
			.withYear(Integer.parseInt(generationParams.getYear()))
			.withMonthOfYear(Integer.parseInt(generationParams.getMonth()));
	}

	private LocalDate getFirstOfMonth(InvoiceGenerationParams generationParams) {
		return getLocalDate(generationParams).dayOfMonth().withMinimumValue();
	}

	public List<InvoiceModel> findInvoices(final LocalDate dateFrom, final LocalDate dateTo) {
		// TODO Auto-generated method stub
		return null;
	}
}
