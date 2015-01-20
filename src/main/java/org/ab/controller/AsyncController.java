package org.ab.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.ab.model.js.PackageDetails;
import org.ab.service.ContractPackageService;
import org.ab.service.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value="/getInvoiceContent/{id}",  produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getInvoiceContent(@PathVariable final int id) {
		return this.invoiceService.getInvoiceHtmlContent(id);
	}

	@RequestMapping(value="/getPackageDetails/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPackageDetails(@PathVariable final int id) {
		final PackageDetails packageDetails = this.packageService.getPackage(String.valueOf(id));
		return packageDetails.serialize();
	}

	@RequestMapping(value="/clearSubscriberContext",  produces = "text/html; charset=utf-8")
	@ResponseBody
	public void getInvoiceContent(final HttpServletRequest request) {
		request.getSession().removeAttribute("subscriber");

	}
}
