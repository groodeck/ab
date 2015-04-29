package org.ab.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ab.model.js.PackageDetails;
import org.ab.model.js.ServiceDetails;
import org.ab.service.ContractPackageService;
import org.ab.service.CorrectionService;
import org.ab.service.InvoicesService;
import org.ab.util.DecimalWriter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/async")
public class AsyncController {
	private static final Logger logger = Logger.getLogger(AsyncController.class.getName());

	@Autowired
	private ContractPackageService packageService;

	@Autowired
	private InvoicesService invoiceService;

	@Autowired
	private CorrectionService correctionService;


	@RequestMapping(value="/getAmountWords",  produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getAmountWords(final HttpServletRequest request) {
		final String value = request.getParameter("value");
		return DecimalWriter.getDecimalSpoken(value);
	}

	@RequestMapping(value="/getPackages/{clientType}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getContractPackages(@PathVariable final String clientType) {
		final Map<String, String> packages = packageService.getPackageDictionary(clientType);
		return new JSONSerializer().serialize(packages);
	}

	@RequestMapping(value="/getCorrectionContent/{id}",  produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getCorrectionContent(@PathVariable final int id) {
		return correctionService.getCorrectionHtmlContent(id);
	}

	@RequestMapping(value="/getCorrectionFile/{id}",  produces = "text/html; charset=utf-8")
	@ResponseBody
	public void getCorrectionFile(@PathVariable final int id, final HttpServletResponse response) {
		final String filePath = correctionService.getCorrectionFile(id);
		response.setContentType("application/octet-stream");
		//TODO: get filename from Service layer
		response.setHeader("Content-Disposition", "filename=\"KOR_000001_02_2015.pdf\"");
		final File file = new File(filePath);
		try {
			FileUtils.copyFile(file, response.getOutputStream());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/clearSubscriberContext",  produces = "text/html; charset=utf-8")
	@ResponseBody
	public void getInvoiceContent(final HttpServletRequest request) {
		request.getSession().removeAttribute("subscriber");
	}

	@RequestMapping(value="/getInvoiceContent/{id}",  produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getInvoiceContent(@PathVariable final int id) {
		return invoiceService.getInvoiceHtmlContent(id);
	}

	@RequestMapping(value="/getInvoiceFile/{id}",  produces = "application/pdf; charset=utf-8")
	@ResponseBody
	public void getInvoiceFile(@PathVariable final int id, final HttpServletResponse response) {
		final String filePath = invoiceService.getInvoiceFile(id);
		final File file = new File(filePath);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "filename=\"" + file.getName() + "\"");
		try {
			FileUtils.copyFile(file, response.getOutputStream());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getPackageDetails/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getPackageDetails(@PathVariable final int id) {
		final PackageDetails packageDetails = packageService.getPackage(String.valueOf(id));
		return packageDetails.serialize();
	}

	@RequestMapping(value="/getServiceDetails/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getServiceDetails(@PathVariable final int id) {
		final ServiceDetails packageDetails =packageService.getService(String.valueOf(id));
		return packageDetails.serialize();
	}
}
