package org.ab.service.generator;

import java.util.List;

import org.joda.time.LocalDate;

public abstract class NumberGenerator {

	public String generate(final LocalDate dateFrom, final LocalDate dateTo) {
		final long lastDocumentSequence = getLastDocumentSequence(dateFrom, dateTo);
		return generate(lastDocumentSequence, dateFrom);
	}

	public String generate(final long lastDocumentSequence, final LocalDate currentDate) {
		final String sequenceNumber = String.format("%06d", lastDocumentSequence+1);
		final String month = String.format("%02d", currentDate.getMonthOfYear());
		return String.format(getNumberTemplate(), sequenceNumber, month, currentDate.getYear());
	}

	protected abstract List<String> getDocumentNumbers(final LocalDate dateFrom, final LocalDate dateTo);

	public long getLastDocumentSequence(final LocalDate dateFrom, final LocalDate dateTo) {
		final List<String> documentNumbers = getDocumentNumbers(dateFrom, dateTo);
		int maxInvoiceNumber = 0;
		for(final String invoiceNumber : documentNumbers){
			final String[] numberParts = invoiceNumber.split("/");
			final String numberSequenceStr = numberParts[1];
			final int numberSequence = Integer.parseInt(numberSequenceStr);
			if(numberSequence > maxInvoiceNumber){
				maxInvoiceNumber = numberSequence;
			}
		}
		return maxInvoiceNumber;
	}
	protected abstract String getNumberTemplate();
}
