package org.ab.controller;

import javax.servlet.http.HttpServletResponse;

import org.ab.model.dictionary.SelectValueService;
import org.ab.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Optional;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	public class ReportParameters {
		private String reportType;

		public String getReportType() {
			return reportType;
		}

		public void setReportType(final String reportType) {
			this.reportType = reportType;
		}
	}

	@Autowired
	private SelectValueService selectValuesService;

	@Autowired
	private FileResponseHandler fileResponse;

	@Autowired
	private ReportsService reportsService;

	@RequestMapping(value="/generateReport/{reportType}",  produces = "application/xlsx; charset=utf-8")
	@ResponseBody
	public void getInvoiceFile(@PathVariable final String reportType, final HttpServletResponse response) {
		final Optional<String> filePath = reportsService.generateReport(reportType);
		if(filePath.isPresent()){
			fileResponse.sendToClient(filePath.get(), response);
		}
	}

	@RequestMapping
	public String handleInitEntry(final Model model) {
		model.addAllAttributes(selectValuesService.getReportsDictionaries());
		model.addAttribute("reportParameters", new ReportParameters());
		return "reports";
	}
}