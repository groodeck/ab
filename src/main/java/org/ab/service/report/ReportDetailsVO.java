package org.ab.service.report;

public class ReportDetailsVO {

	private int row;
	private int cell;
	private DataCell data;
	public ReportDetailsVO(final int row, final int cell, final DataCell data) {
		super();
		this.row = row;
		this.cell = cell;
		this.data = data;
	}

	public int getCell() {
		return cell;
	}
	public DataCell getData() {
		return data;
	}
	public int getRow() {
		return row;
	}
	public void setCell(final int cell) {
		this.cell = cell;
	}
	public void setData(final DataCell data) {
		this.data = data;
	}
	public void setRow(final int row) {
		this.row = row;
	}
}
