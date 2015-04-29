package org.ab.service.generator;

import java.util.List;

import org.ab.dao.CorrectionDao;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorrectionNumberGenerator extends NumberGenerator{

	private static final String CORRECTION_NUMBER_TEMPLATE = "KOR/%s/%s/%s";

	@Autowired
	private CorrectionDao correctionDao;

	@Override
	protected List<String> getDocumentNumbers(final LocalDate dateFrom,
			final LocalDate dateTo) {
		return correctionDao.getCorrectionNumbers(dateFrom, dateTo);
	}

	@Override
	protected String getNumberTemplate() {
		return CORRECTION_NUMBER_TEMPLATE;
	}
}
