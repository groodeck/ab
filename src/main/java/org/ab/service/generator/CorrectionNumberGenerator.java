package org.ab.service.generator;

import org.ab.dao.CorrectionDao;
import org.ab.model.InvoiceModel;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CorrectionNumberGenerator {

	@Autowired
	private CorrectionDao correctionDao;

	private long getDocumentCount(final LocalDate dateFrom, final LocalDate dateTo) {
		return correctionDao.getCorrectionCount(dateFrom, dateTo);
	}

	public String generate(final InvoiceModel invoiceModel) {
		final LocalDate dateFrom = invoiceModel.getSettlementPeriodStart();
		final LocalDate dateTo = invoiceModel.getSettlementPeriodEnd();
		final long correctionCount = getDocumentCount(dateFrom, dateTo);
		final String sequenceNumber = String.format("%06d", correctionCount+1);
		final String month = String.format("%02d", dateFrom.getMonthOfYear());
		return String.format("KOR/%s/%s/%s", sequenceNumber, month, dateFrom.getYear());
	}
}
