package org.ab.controller;

import java.security.Principal;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.ab.model.Contract;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.ContractService;
import org.ab.service.SubscriberService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {
	private static final Logger logger = Logger.getLogger(SubscriberController.class.getName());

	@Autowired
	private SelectValueService selectValuesService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private SubscriberService subscriberService;

	private String convertToString(final int number, final int i) {
		String result = String.valueOf(number);
		for(;result.length()<i;){
			result = "0".concat(result);
		}
		return result;
	}

	private String generateNewIdn() {
		final String maxClientId = subscriberService.getMaxClientId();
		final int nextClientId = Integer.parseInt(maxClientId) + 1;
		return convertToString(nextClientId, 3);
	}

	@RequestMapping("/edit/{subscriberId}")
	public String handleEditSubscriber(@PathVariable final int subscriberId, final Model model, final HttpServletRequest request) {
		final SubscriberModel subscriber = subscriberService.getSubscriberDetails(subscriberId);
		if(subscriber == null){
			model.addAttribute("uiMessage", "Nie mo¿na pobraæ danych abonenta");
			return "subscribers";
		} else {
			model.addAttribute("subscriber", subscriber);
			model.addAllAttributes(selectValuesService.getSubscriberDictionaries());
			request.getSession().setAttribute("subscriber", subscriber);
			return "subscriber";
		}
	}

	@RequestMapping("/new")
	public String handleInitEntry(final Model model) {
		final SubscriberModel newSubscriber = new SubscriberModel();
		initSubscriber(newSubscriber);
		model.addAttribute("subscriber", newSubscriber);
		model.addAllAttributes(selectValuesService.getSubscriberDictionaries());
		return "subscriber";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final SubscriberModel subscriber, final Model model, final Principal principal) {
		System.out.println("saving subscriber " + subscriber);
		subscriberService.save(subscriber, principal.getName());
		model.addAttribute("subscriber", subscriber);
		model.addAllAttributes(selectValuesService.getSubscriberDictionaries());
		model.addAttribute("uiMessage", "Zapisano dane klienta i umowy");
		return "subscriber";
	}

	private void initSubscriber(final SubscriberModel newSubscriber) {
		newSubscriber.setSubscriberIdn(generateNewIdn());
		final LocalDate today = LocalDate.now();
		final Contract currentContract = newSubscriber.getCurrentContract();
		currentContract.setContractSignDate(today.toString("yyyy-MM-dd"));
		currentContract.setContractActivationDate(today.toString("yyyy-MM-dd"));
		final long nextContractId = contractService.getContractsSignCount(today) + 1;
		currentContract.setContractIdn(today.toString("ddMMyyyy") + "-" + nextContractId);
	}
}
