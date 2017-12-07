package com.company.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Requests for the application home page.
 */
@Controller
@ApiIgnore
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Initialazing swagger... The client locale is {}.", locale);
		return "redirect:swagger-ui.html";
	}

}
