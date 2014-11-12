package org.ab.controller;

import java.util.logging.Logger;

import org.ab.model.SubscriberModel;
import org.ab.model.dictionary.ClientType;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.CityService;
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
	
	@RequestMapping("/new")
    public String handleInitEntry(final Model model) {
		model.addAttribute("subscriber", new SubscriberModel());
		model.addAllAttributes(selectValuesService.getSubscriberDictionaries());
		return "subscriber";
    }
	
	@RequestMapping("/save")
    public String handleSearchAction(final SubscriberModel subscriber, final Model model) {
		System.out.println("saving subscriber " + subscriber);
		model.addAttribute("subscriber", subscriber);
		model.addAllAttributes(selectValuesService.getSubscriberDictionaries());
		return "subscriber";
    }
	
	
}
