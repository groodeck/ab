package org.ab.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	
	@RequestMapping
    public String showLoginPage(final Model model) {
		return "login";
    }
	
}
