package org.ab.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/subscribers")
public class SubscribersController {
	private static final Logger logger = Logger.getLogger(SubscribersController.class.getName());
	
	@RequestMapping
    public String handleInitEntry(final Model model) {
		return "subscribers";
    }
	
	@RequestMapping("/search")
    public String handleSearchAction(final @RequestParam("searchPhrase") String searchPhrase, 
    		final Model model) {
		System.out.println("search phrase: " + searchPhrase);
		return "subscribers";
    }
}
