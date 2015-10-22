package org.ab.service;

import org.ab.dao.ContractDurationDao;
import org.ab.exception.CannotFindReportImplementation;
import org.ab.model.dictionary.ReportType;
import org.ab.service.report.ReportGenerator;
import org.ab.service.report.ReportGeneratorFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

@Component
public class ReportsService {

	private static final Logger log = Logger.getLogger(ReportsService.class);

	@Autowired
	private ContractDurationDao contractDurationDao;

	@Autowired
	private ReportGeneratorFactory reportGeneratorFactory;

	@Transactional
	public Optional<String> generateReport(final String reportTypeName) {
		try {
			final ReportType reportType = ReportType.valueOf(reportTypeName);
			final ReportGenerator reportGenerator = reportGeneratorFactory.get(reportType);
			return reportGenerator.generateReport();
		} catch (final CannotFindReportImplementation e) {
			log.error("CannotFindReportImplementation", e);
			return Optional.absent();
		}
	}
}
