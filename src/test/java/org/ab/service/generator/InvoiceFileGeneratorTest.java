package org.ab.service.generator;

import org.ab.model.InvoiceModel;
import org.ab.service.generator.InvoiceFileGenerator;
import org.junit.Test;

public class InvoiceFileGeneratorTest {

	private final InvoiceFileGenerator service = new InvoiceFileGenerator();

	@Test
	public void shouldPrintPdf(){
		// given

		// when
		service.generateSingleInvoice(new InvoiceModel.Builder().build());

		// then
	}
}
