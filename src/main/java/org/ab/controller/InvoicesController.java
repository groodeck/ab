package org.ab.controller;

import static org.ab.util.Translator.toLocalDate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ab.model.CorrectionModel;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.InvoiceModel;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.CorrectionService;
import org.ab.service.InvoicesService;
import org.assertj.core.util.Collections;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {

	@Autowired
	private InvoicesService invoicesService;

	@Autowired
	private CorrectionService correctionService;

	@Autowired
	private SelectValueService selectValuesService;

	private ImmutableMap<Integer,CorrectionModel>findCorrections(final List<InvoiceModel> invoices) {
		final List<CorrectionModel> corrections = correctionService.findCorrections(invoices);
		return Maps.uniqueIndex(corrections, new Function<CorrectionModel, Integer>(){
			@Override
			public Integer apply(final CorrectionModel arg0) {
				return arg0.getInvoice().getInvoiceId();
			}
		});
	}

	private String getGenerationResultsMessage(final List<InvoiceModel> invoices) {
		if(Collections.isNullOrEmpty(invoices)){
			return "Nie wygenerowano faktur";
		} else {
			return String.format("Wygenerowano faktury: %s szt.",invoices.size());
		}
	}

	private String getSubscriberIdn(final HttpSession session) {
		final SubscriberModel subscriber = (SubscriberModel)session.getAttribute("subscriber");
		if(subscriber == null){
			return null;
		} else {
			return subscriber.getSubscriberIdn();
		}
	}

	@RequestMapping
	public String handleInitEntry(final Model model) {
		model.addAttribute("generationParams", new InvoiceGenerationParams(LocalDate.now()));
		model.addAllAttributes(selectValuesService.getInvoicesDictionaries());
		return "invoices";
	}

	@RequestMapping("/generate")
	public String handleInvoicesGeneration(final @ModelAttribute("generationParams") InvoiceGenerationParams generationParams,
			final Model model, final HttpServletRequest request) {
		final SubscriberModel subscriber = (SubscriberModel)request.getSession().getAttribute("subscriber");
		final List<InvoiceModel> invoices = invoicesService.generateInvoices(generationParams, subscriber);
		model.addAttribute("uiMessage", getGenerationResultsMessage(invoices));

		model.addAllAttributes(selectValuesService.getInvoicesDictionaries());
		model.addAttribute("invoices", invoices);
		return "invoices";
	}

	@RequestMapping("/search")
	public String handleSearchAction(final @RequestParam("searchDateFrom") String searchDateFrom,
			final @RequestParam("searchDateTo") String searchDateTo, final Model model, final HttpServletRequest request) {
		final String subscriberIdn = getSubscriberIdn(request.getSession());
		final LocalDate dateFrom = toLocalDate(searchDateFrom);
		final LocalDate dateTo = toLocalDate(searchDateTo);
		final List<InvoiceModel> invoices = invoicesService.findInvoices(subscriberIdn, dateFrom, dateTo);
		final ImmutableMap<Integer,CorrectionModel> correctionsMap = findCorrections(invoices);
		model.addAttribute("invoices", invoices);
		model.addAttribute("corrections", correctionsMap);
		handleInitEntry(model);
		return "invoices";
	}
}