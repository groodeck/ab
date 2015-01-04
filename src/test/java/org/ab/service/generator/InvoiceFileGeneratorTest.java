package org.ab.service.generator;

import org.ab.service.generator.Invoice;
import org.ab.service.generator.InvoiceFileGenerator;
import org.junit.Test;

public class InvoiceFileGeneratorTest {

	private final InvoiceFileGenerator service = new InvoiceFileGenerator();

	@Test
	public void shouldPrintPdf(){
		// given

		// when
		service.generateSingleInvoice(new Invoice.Builder().build());

		// then
	}
}
