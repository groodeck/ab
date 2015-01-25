package org.ab.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ab.model.SubscriberModel;
import org.ab.service.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/subscribers")
public class SubscribersController {

	@Autowired
	private SubscribersService subscribersService;

	@RequestMapping
	public String handleInitEntry(final Model model, final HttpServletRequest request) {
		final List<SubscriberModel> subscribers =
				(List<SubscriberModel>)request.getSession().getAttribute("subscriberList");
		if(subscribers != null){
			model.addAttribute("subscribers", subscribers);
		}
		return "subscribers";
	}

	@RequestMapping("/search")
	public String handleSearchAction(final @RequestParam("searchPhrase") String searchPhrase,
			final Model model, final HttpServletRequest request) {
		final List<SubscriberModel> subscribers =
				subscribersService.findSubscribers(searchPhrase, null, null);
		model.addAttribute("subscribers", subscribers);
		request.getSession().setAttribute("subscriberList", subscribers);
		return "subscribers";
	}
}