package org.ab.service.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.ab.dao.InvoiceDao;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceNumberGeneratorTest {

	@Mock
	private InvoiceDao invoiceDao;

	@InjectMocks
	private final InvoiceNumberGenerator generator = new InvoiceNumberGenerator();

	@Test
	public void shouldGenerateNextInvoiceNumber(){
		// given
		final LocalDate dateFrom = LocalDate.parse("2014-01-01");
		final LocalDate dateTo = LocalDate.parse("2014-01-31");
		given(invoiceDao.getInvoiceNumbers(dateFrom, dateTo))
		.willReturn(Lists.newArrayList("FVAT/000003l/02/2011"));

		// when
		final String invoiceNumber = generator.generate(3, dateFrom);

		// then
		assertThat(invoiceNumber).isEqualTo("FVAT/000004/01/2014");

	}

	@Test
	public void shouldGenerateNextInvoiceNumberByDates(){
		// given
		final LocalDate dateFrom = LocalDate.parse("2014-01-01");
		final LocalDate dateTo = LocalDate.parse("2014-01-31");
		given(invoiceDao.getInvoiceNumbers(dateFrom, dateTo))
		.willReturn(Lists.newArrayList("FVAT/000031/01/2014", "FVAT/000033/01/2014"));

		// when
		final String invoiceNumber = generator.generate(dateFrom, dateTo);

		// then
		assertThat(invoiceNumber).isEqualTo("FVAT/000034/01/2014");

	}

	@Test
	public void shouldGetLastDocumentNumberSequence(){
		// given
		final LocalDate dateFrom = LocalDate.parse("2014-01-01");
		final LocalDate dateTo = LocalDate.parse("2014-01-31");
		given(invoiceDao.getInvoiceNumbers(dateFrom, dateTo))
		.willReturn(Lists.newArrayList("FVAT/000031/01/2014", "FVAT/000034/01/2014", "FVAT/000016/01/2014"));

		// when
		final long invoiceNumber = generator.getLastDocumentSequence(dateFrom, dateTo);

		// then
		assertThat(invoiceNumber).isEqualTo(34);

	}
}
