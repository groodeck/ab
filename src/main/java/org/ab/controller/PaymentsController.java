package org.ab.controller;

import static org.ab.util.Translator.toLocalDate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ab.model.PaymentModel;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.ContractPackageService;
import org.ab.service.PaymentsService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payments")
public class PaymentsController {

	@Autowired
	private ContractPackageService contractPackageService;

	@Autowired
	private PaymentsService paymentsService;

	@Autowired
	private SelectValueService selectValuesService;

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
		model.addAttribute("searchDateFrom", LocalDate.now().minusMonths(3));
		model.addAttribute("searchDateTo", LocalDate.now());
		return "payments";
	}

	@RequestMapping("/search")
	public String handleSearchAction(final @RequestParam("searchDateFrom") String searchDateFrom,
			final @RequestParam("searchDateTo") String searchDateTo, final Model model, final HttpServletRequest request) {
		final String subscriberIdn = getSubscriberIdn(request.getSession());
		final List<PaymentModel> payments =
				paymentsService.findPayments(subscriberIdn, toLocalDate(searchDateFrom), toLocalDate(searchDateTo));
		model.addAttribute("payments", payments);
		handleInitEntry(model);
		return "payments";
	}
}