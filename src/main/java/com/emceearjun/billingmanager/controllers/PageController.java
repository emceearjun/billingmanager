package com.emceearjun.billingmanager.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@Value("${page.billingmanager}")
	private String BILLING_MANAGER_PAGE;
	
	@GetMapping(path = "/billingmanager")
	public String getBillingManagerPage() {
		return BILLING_MANAGER_PAGE + "index.html";
	}

}
