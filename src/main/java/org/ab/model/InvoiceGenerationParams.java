package org.ab.model;

import org.joda.time.LocalDate;

public class InvoiceGenerationParams{
	private String month;
	private String year;

	public InvoiceGenerationParams() {
	}
	public InvoiceGenerationParams(final LocalDate initDate) {
		this.month = String.valueOf(initDate.getMonthOfYear());
		this.year = String.valueOf(initDate.getYear());
	}
	public String getMonth() {
		return month;
	}
	public String getYear() {
		return year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setYear(String year) {
		this.year = year;
	}
};
