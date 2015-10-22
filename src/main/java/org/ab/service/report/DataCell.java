package org.ab.service.report;

import java.math.BigDecimal;

import org.joda.time.LocalDate;

public class DataCell {

	private final CellType cellType;
	private final Object value;

	public DataCell(final Object value, final CellType cellType) {
		this.cellType = cellType;
		this.value = value;
	}

	public CellType getCellType() {
		return cellType;
	}

	public LocalDate getDateValue() {
		return (LocalDate)value;
	}

	public BigDecimal getDecimalValue() {
		return (BigDecimal)value;
	}

	public Integer getIntegerValue() {
		return (Integer)value;
	}

	public String getStringValue() {
		return (String)value;
	}

}
