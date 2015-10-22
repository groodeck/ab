package org.ab.service.report;

import java.util.Set;

import org.ab.annotation.ReportGeneratorOfType;
import org.ab.exception.CannotFindReportImplementation;
import org.ab.model.dictionary.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportGeneratorFactory {

	@Autowired
	private Set<ReportGenerator> generators;

	public ReportGenerator get(final ReportType reportType) throws CannotFindReportImplementation {
		for(final ReportGenerator particularGenerator : generators){
			final ReportGeneratorOfType annotation = particularGenerator.getClass().getAnnotation(ReportGeneratorOfType.class);
			if (annotation != null && annotation.value() == reportType){
				return particularGenerator;
			}
		}
		throw new CannotFindReportImplementation(reportType.name());
	}

}
