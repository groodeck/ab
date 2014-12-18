package org.ab.service.generator;

import java.math.BigDecimal;

import org.junit.Test;

public class InvoiceContentGeneratorTest {

	InvoiceContentGenerator generator = new InvoiceContentGenerator();

	@Test
	public void shouldGenerateInvoice(){
		// given
		final InvoiceServiceRecord serviceRecord = new InvoiceServiceRecord.Builder()
			.withLp(1)
			.withServiceName("nazwa us³ugi")
			.withQuantity(1)
			.withNetAmount(new BigDecimal("10"))
			.withVatRate(7)
			.withVatAmount(new BigDecimal("5"))
			.withGrossAmount(new BigDecimal("15"))
			.build();
		final Invoice invoice = new Invoice.Builder().withServiceRecord(serviceRecord).build();

		// when
		final String html = this.generator.generateHtml(invoice);

		// then
		System.out.println(html);
	}
}
