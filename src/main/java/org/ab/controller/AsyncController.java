package org.ab.controller;

import java.util.logging.Logger;

import org.ab.model.js.PackageDetails;
import org.ab.service.ContractPackageService;
import org.ab.service.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/async")
public class AsyncController {
	private static final Logger logger = Logger.getLogger(AsyncController.class.getName());

	@Autowired
	private ContractPackageService packageService;

	@Autowired
	private InvoicesService invoiceService;

	@RequestMapping(value="/getInvoiceContent/{id}", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String getInvoiceContent(@PathVariable final int id) {
		return invoiceService.getInvoiceHtmlContent(id);
	}

	@RequestMapping(value="/getPackageDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPackageDetails(@PathVariable final int id) {
		final PackageDetails packageDetails = packageService.getPackage(String.valueOf(id));
		return packageDetails.serialize();
	}
}
