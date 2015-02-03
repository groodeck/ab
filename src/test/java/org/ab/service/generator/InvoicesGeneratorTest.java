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

@RunWith(MockitoJUnitRunner.class)
public class InvoicesGeneratorTest {

	@Mock
	private InvoiceDao invoiceDao;

	@InjectMocks
	private final InvoicesGenerator generator = new InvoicesGenerator();

	@Test
	public void shouldGenerateNextInvoiceNumber(){
		// given
		final LocalDate dateFrom = LocalDate.parse("2014-01-01");
		final LocalDate dateTo = LocalDate.parse("2014-01-31");
		given(invoiceDao.getInvoiceCount(dateFrom, dateTo)).willReturn(3l);

		// when
		final String invoiceNumber = generator.generateInvoiceNumber(3, dateFrom);

		// then
		assertThat(invoiceNumber).isEqualTo("FVAT/000004/01/2014");

	}
}
