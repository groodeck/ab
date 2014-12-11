package org.ab.controller;

import java.util.Dictionary;
import java.util.List;

import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.InvoicesService;
import org.ab.service.SubscribersService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {
	
	@Autowired
	private InvoicesService invoicesService;
	
	@Autowired
	private SelectValueService selectValuesService;
	
	@RequestMapping
    public String handleInitEntry(final Model model) {
		model.addAttribute("generationParams", new InvoiceGenerationParams(LocalDate.now()));
		model.addAllAttributes(selectValuesService.getInvoicesDictionaries());
		return "invoices";
    }
	
	@RequestMapping("/generate")
    public String handleInvoicesGeneration(final @ModelAttribute("generationParams") InvoiceGenerationParams generationParams, 
    		final Model model) {
		invoicesService.generateInvoices(generationParams);
		System.out.println(String.format("year: %s, month: %s", generationParams.getYear(), generationParams.getMonth()));
		model.addAttribute("uiMessage", "Wygenerowano faktury");
		
		model.addAllAttributes(selectValuesService.getInvoicesDictionaries());
		model.addAttribute("invoices", Lists.newArrayList());
		return "invoices";
    }
	
	@RequestMapping("/search")
    public String handleSearchAction(final @RequestParam("dateFrom") String dateFrom, 
    		final @RequestParam("dateTo") String dateTo, final Model model) {
		final List<InvoiceModel> invoices = 
				invoicesService.findInvoices(LocalDate.now(), LocalDate.now());
		model.addAttribute("invoices", invoices);
		return "invoices";
    }
}