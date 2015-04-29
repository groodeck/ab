package org.ab.service.generator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.ab.dao.CorrectionDao;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class CorrectionNumberGeneratorTest {

	@Mock
	private CorrectionDao correctionDao;

	@InjectMocks
	private final CorrectionNumberGenerator generator = new CorrectionNumberGenerator();

	@Test
	public void shouldGenerateNextInvoiceNumber(){
		// given
		final LocalDate dateFrom = LocalDate.parse("2014-01-01");
		final LocalDate dateTo = LocalDate.parse("2014-01-31");
		given(correctionDao.getCorrectionNumbers(dateFrom, dateTo))
		.willReturn(Lists.newArrayList("KOR/000003l/02/2011"));

		// when
		final String correctionNumber = generator.generate(3, dateFrom);

		// then
		assertThat(correctionNumber).isEqualTo("KOR/000004/01/2014");

	}

	@Test
	public void shouldGenerateNextInvoiceNumberByDates(){
		// given
		final LocalDate dateFrom = LocalDate.parse("2014-01-01");
		final LocalDate dateTo = LocalDate.parse("2014-01-31");
		given(correctionDao.getCorrectionNumbers(dateFrom, dateTo))
		.willReturn(Lists.newArrayList("KOR/000031/01/2014", "KOR/000033/01/2014"));

		// when
		final String correctionNumber = generator.generate(dateFrom, dateTo);

		// then
		assertThat(correctionNumber).isEqualTo("KOR/000034/01/2014");

	}

	@Test
	public void shouldGetLastDocumentNumberSequence(){
		// given
		final LocalDate dateFrom = LocalDate.parse("2014-01-01");
		final LocalDate dateTo = LocalDate.parse("2014-01-31");
		given(correctionDao.getCorrectionNumbers(dateFrom, dateTo))
		.willReturn(Lists.newArrayList("KOR/000031/01/2014", "KOR/000034/01/2014", "KOR/000016/01/2014"));

		// when
		final long correctionNumber = generator.getLastDocumentSequence(dateFrom, dateTo);

		// then
		assertThat(correctionNumber).isEqualTo(34);

	}
}
