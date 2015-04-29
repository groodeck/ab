package org.ab.service.generator;

import java.util.List;

import org.ab.dao.InvoiceDao;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceNumberGenerator extends NumberGenerator{

	private static final String INVOICE_NUMBER_TEMPLATE = "FVAT/%s/%s/%s";

	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	protected List<String> getDocumentNumbers(final LocalDate dateFrom, final LocalDate dateTo) {
		return invoiceDao.getInvoiceNumbers(dateFrom, dateTo);
	}

	@Override
	protected String getNumberTemplate() {
		return INVOICE_NUMBER_TEMPLATE;
	}
}
