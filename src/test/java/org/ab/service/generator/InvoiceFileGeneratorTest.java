package org.ab.service.generator;

import org.ab.model.InvoiceModel;
import org.junit.Test;

public class InvoiceFileGeneratorTest {

	private final InvoiceFileGenerator service = new InvoiceFileGenerator();

	@Test
	public void shouldPrintPdf(){
		// given
		final InvoiceModel invoice = new InvoiceModel.Builder().build();

		// when
		service.generateSingleDocument(invoice.getInvoiceNumber(), invoice.getHtmlContent());

		// then
	}
}
