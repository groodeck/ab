package org.ab.service.report;

import static org.ab.service.generator.FileGenerator.DOWNLOAD_DIR;
import static org.ab.service.generator.FileGenerator.RESOURCE_DIR;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class XlsFileGenerator {

	private static final String TEMPLATES_PATH = RESOURCE_DIR + "template/";

	private void fillSingleSheet(final Sheet sheet, final DataSheet dataSheet) {
		final int rowOffset = dataSheet.getDataRowsOffset();
		final int cellOffset = 0;
		for(int rowNumber=0; rowNumber<dataSheet.getRows().size(); rowNumber++){
			final List<DataCell> singleRow = dataSheet.getRow( rowNumber );
			final Row row = getOrCreateRow(sheet, rowOffset + rowNumber);
			for( int cellNumber=0; cellNumber<singleRow.size(); cellNumber++ ){
				final DataCell cellData = dataSheet.getRow(rowNumber).get(cellNumber);
				if(cellData != null){
					final Cell cell = getOrCreateCell(row, cellOffset + cellNumber);
					setCellValue(cell,  cellData);
				}
			}
		}
	}

	public String generate(final String templateFileName, final String outputFileName,
			final List<DataSheet> reportDataMap)  throws IOException, InvalidFormatException {

		final Workbook wb = getWorkbook(templateFileName);

		for(final DataSheet dataSheet : reportDataMap){
			final String worksheetName = dataSheet.getWorksheetName();
			final Sheet targetSheet = wb.getSheet(worksheetName);
			fillSingleSheet(targetSheet, dataSheet);
			printReportDetails(dataSheet.getReportDetailsList(), targetSheet);
		}
		return writeOutputFile(outputFileName, wb);
	}

	private Cell getOrCreateCell(final Row row, final int cellNumber) {
		Cell cell = row.getCell(cellNumber);
		if(cell== null){
			cell = row.createCell(cellNumber);
		}
		return cell;
	}

	private Row getOrCreateRow(final Sheet sheet, final int rowNumber) {
		Row row = sheet.getRow(rowNumber);
		if(row == null){
			row = sheet.createRow(rowNumber);
		}
		return row;
	}

	private Workbook getWorkbook(final String templateFileName)
			throws FileNotFoundException, IOException, InvalidFormatException {

		final InputStream inp = new FileInputStream(TEMPLATES_PATH + templateFileName);
		return WorkbookFactory.create(inp);
	}

	private void printReportDetails(
			final List<ReportDetailsVO> reportDetailsList, final Sheet sheet) {

		for(final ReportDetailsVO reportDetailCell : reportDetailsList){
			Cell cell = sheet.getRow(reportDetailCell.getRow()).getCell(reportDetailCell.getCell());
			if(cell == null) {
				cell = sheet.getRow(reportDetailCell.getRow()).createCell(reportDetailCell.getCell());
			}
			setCellValue(cell, reportDetailCell.getData());
		}
	}

	private void setCellValue(final Cell xlsCell, final DataCell data) {

		switch (data.getCellType()) {
		case TEXT:
			final CreationHelper createHelper = xlsCell.getRow().getSheet().getWorkbook().getCreationHelper();
			xlsCell.setCellValue(createHelper.createRichTextString(data.getStringValue()));
			break;

		case WRAPABLE_TEXT:
			xlsCell.setCellValue(data.getStringValue());
			xlsCell.getCellStyle().setWrapText(true);
			break;

		case LOCAL_DATE:
			final LocalDate dateValue = data.getDateValue();
			if(dateValue != null){
				xlsCell.setCellValue(dateValue.toString());
			}
			break;

		case BIG_DECIMAL:
			final BigDecimal decimalValue = data.getDecimalValue();
			xlsCell.setCellValue(decimalValue.doubleValue());
			break;

		case INT:
			final Integer intValue = data.getIntegerValue();
			if(intValue != null){
				xlsCell.setCellValue(intValue.toString());
			}
			break;

		default:
			break;
		}
	}

	private String writeOutputFile(final String outputFileName, final Workbook wb)
			throws FileNotFoundException, IOException {
		// Write the output to a file
		final String reportRelatedDir =  DOWNLOAD_DIR + outputFileName;

		final FileOutputStream fileOut = new FileOutputStream(reportRelatedDir);
		wb.write(fileOut);
		fileOut.close();
		return reportRelatedDir;
	}
}
