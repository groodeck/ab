package org.ab.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class.getName());
	
	@RequestMapping
    public String handleLoginAction(final String j_username, final String j_password, final Model model) {
		logger.info(String.format("Login action for %s/%s", j_username, j_password));
		return "home";
    }
}
