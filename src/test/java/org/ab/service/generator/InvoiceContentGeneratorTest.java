package org.ab.service.generator;

import java.math.BigDecimal;

import org.ab.model.InvoiceModel;
import org.joda.time.LocalDate;
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
		final InvoiceModel invoice = new InvoiceModel.Builder()
			.withServiceRecord(serviceRecord)
			.withInvoiceNumber("1/2/2014")
			.withCreateDate(LocalDate.now())
			.withReceiveDate(LocalDate.now())
			.withNetAmount(new BigDecimal(100))
			.withVatAmount(new BigDecimal(22))
			.withGrossAmount(new BigDecimal(122))
			.withGrossAmountWords("sto dwadziescia dwa z³ote 00/100")
			.withSeller(createDefaultSeller())
			.withSubscriber(createDefaultSubscriber())
			.build();

		// when
		final String html = this.generator.generateHtml(invoice);

		// then
		System.out.println(html);
	}

	private InvoiceParticipant createDefaultSubscriber() {
		return new InvoiceParticipant.Builder()
			.withName("Jan Kowalski")
			.withAddressStreet("Akacjowa 15/8")
			.withAddressCity("85-788 Sierpc")
			.withPhone("855-48-98")
			.build();
	}

	private InvoiceParticipant createDefaultSeller() {
		return new InvoiceParticipant.Builder()
			.withName("JaworNET")
			.withAddressStreet("Noakowskiego 4")
			.withAddressCity("85-753 Sierpc")
			.withRegon("4568521")
			.withNip("458-896-22-45")
			.build();
	}
}
