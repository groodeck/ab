package org.ab.controller;

import java.util.Dictionary;
import java.util.List;

import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.SubscribersService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {
	
	@Autowired
	private SubscribersService subscribersService;
	
	@Autowired
	private SelectValueService selectValuesService;
	
	@RequestMapping
    public String handleInitEntry(final Model model) {
		return "invoices";
    }
	
	@RequestMapping("/generate/{year}/{month}")
    public String handleEditSubscriber(@PathVariable int year, @PathVariable int month, final Model model) {
//		final SubscriberModel subscriber = subscriberService.getSubscriberDetails(subscriberId);
		System.out.println(String.format("year: %s, month: %s", year, month));
		model.addAttribute("uiMessage", "Wygenerowano faktury");
		
		model.addAllAttributes(selectValuesService.getInvoicesDictionaries());
		model.addAttribute("invoices", Lists.newArrayList());
		return "invoices";
    }
	
//	@RequestMapping("/search")
//    public String handleSearchAction(final @RequestParam("searchPhrase") String searchPhrase, 
//    		final Model model) {
//		final List<SubscriberModel> subscribers = 
//				subscribersService.findSubscribers(searchPhrase, null, null);
//		model.addAttribute("subscribers", subscribers);
//		return "subscribers";
//    }
}