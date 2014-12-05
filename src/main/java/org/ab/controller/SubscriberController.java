package org.ab.controller;

import java.util.logging.Logger;

import org.ab.model.Contract;
import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.ClientService;
import org.ab.service.ContractService;
import org.ab.service.SubscriberService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {
	private static final Logger logger = Logger.getLogger(SubscriberController.class.getName());
	
	@Autowired
	private SelectValueService selectValuesService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private SubscriberService subscriberService;
	
	@RequestMapping("/new")
    public String handleInitEntry(final Model model) {
		final SubscriberModel newSubscriber = new SubscriberModel();
		initSubscriber(newSubscriber);
		model.addAttribute("subscriber", newSubscriber);
		model.addAllAttributes(selectValuesService.getSubscriberDictionaries());
		return "subscriber";
    }

	private void initSubscriber(final SubscriberModel newSubscriber) {
		newSubscriber.setSubscriberIdn(generateNewIdn());
		final LocalDate today = LocalDate.now();
		final Contract currentContract = newSubscriber.getCurrentContract();
		currentContract.setContractSignDate(today.toString("yyyy-MM-dd"));
		long nextContractId = contractService.getContractsSignCount(today) + 1;
		currentContract.setContractIdn(today.toString("ddMMyyyy") + "-" + nextContractId);
	}
	
	private String generateNewIdn() {
		long maxClientId = clientService.getMaxClientId();
		long nextClientId = maxClientId+1;
		return convertToString(nextClientId, 3);
	}

	private String convertToString(long number, int i) {
		String result = String.valueOf(number);
		for(;result.length()<i;){
			result = "0".concat(result);
		}
		return result;
	}

	@RequestMapping("/save")
    public String handleSaveAction(final SubscriberModel subscriber, final Model model) {
		System.out.println("saving subscriber " + subscriber);
		subscriberService.save(subscriber);
		model.addAttribute("subscriber", subscriber);
		model.addAllAttributes(selectValuesService.getSubscriberDictionaries());
		model.addAttribute("uiMessage", "Zapisano dane klienta i umowy");
		return "subscriber";
    }
	
	
}
