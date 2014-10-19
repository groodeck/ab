package org.ab.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/")
public class LoginController {

	@RequestMapping("/login.app")
    public String welcomeHandler() {
		System.out.println("login");
		return "home";
    }
}
