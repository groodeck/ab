package org.ab.service.report;

import java.util.ArrayList;
import java.util.List;

public class DataSheet {

	private final List<ReportDetailsVO> reportDetailsList;
	private final List<List<DataCell>> rows;
	private final int dataRowsOffset;
	private final String worksheetName;

	public DataSheet(final String worksheetName, final int dataRowsOffset){
		this.worksheetName = worksheetName;
		this.dataRowsOffset = dataRowsOffset;
		rows = new ArrayList<List<DataCell>>();
		reportDetailsList = new ArrayList<ReportDetailsVO>();
	}

	public void addDetailsCell(final int row, final int col, final DataCell dataCell) {
		reportDetailsList.add(new ReportDetailsVO(row, col, dataCell));
	}
	public DataCell getCell(final int rowIndex, final int colIndex){
		return rows.get(rowIndex).get(colIndex);
	}

	public int getDataRowsOffset() {
		return dataRowsOffset;
	}

	public List<ReportDetailsVO> getReportDetailsList() {
		return reportDetailsList;
	}

	public List<DataCell> getRow(final int rowIndex){
		return rows.get(rowIndex);
	}

	public List<List<DataCell>> getRows() {
		return rows;
	}

	public String getWorksheetName() {
		return worksheetName;
	}

}
