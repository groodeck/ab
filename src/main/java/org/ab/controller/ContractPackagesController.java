package org.ab.controller;

import org.ab.model.dictionary.SelectValueService;
import org.ab.service.ContractPackageService;
import org.ab.service.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/packages")
public class ContractPackagesController {

	@Autowired
	private ContractPackageService contractPackageService;

	@Autowired
	private InvoicesService invoicesService;

	@Autowired
	private SelectValueService selectValuesService;

	@RequestMapping
	public String handleInitEntry(final Model model) {
		model.addAttribute("contractPackages", contractPackageService.getAllPackages());
		model.addAllAttributes(selectValuesService.getPackageDictionaries());
		return "packages";
	}
}