package org.ab.service.report;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Optional;

public abstract class BaseReportGenerator implements ReportGenerator {

	private static final Logger log = Logger.getLogger(BaseReportGenerator.class);

	@Autowired
	private XlsFileGenerator reportGenerator;

	@Override
	public Optional<String> generateReport() {
		try {
			final List<DataSheet> reportDataMap = getReportData();
			final String templateFileName = getTemplateFileName();
			final String outputFileName = getOutputFileName();

			final String reportPath = reportGenerator.generate(templateFileName, outputFileName, reportDataMap);
			return Optional.fromNullable(reportPath);
		} catch (InvalidFormatException | IOException e) {
			log.error(e);
			return Optional.absent();
		}

	}

	protected abstract String getOutputFileName();

	protected abstract List<DataSheet> getReportData();

	protected abstract String getTemplateFileName();

}
