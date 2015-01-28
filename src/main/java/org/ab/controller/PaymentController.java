package org.ab.controller;

import java.security.Principal;

import org.ab.model.ContractPackage;
import org.ab.model.InvoicePaymentModel;
import org.ab.model.PaymentModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.ContractPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ContractPackageService contractPackageService;

	@Autowired
	private SelectValueService selectValuesService;

	@RequestMapping("/edit/{packageId}")
	public String handleEditPackage(@PathVariable final int packageId, final Model model) {
		final ContractPackage contractPackage = contractPackageService.getContractPackage(packageId);
		if(contractPackage == null){
			model.addAttribute("uiMessage", "Nie mo¿na pobraæ danych pakietu");
			return "packages";
		} else {
			model.addAttribute("contractPackage", contractPackage);
			model.addAllAttributes(selectValuesService.getPackageDictionaries());
			return "package";
		}
	}

	@RequestMapping("/new")
	public String handleInitEntry(final Model model) {
		final PaymentModel payment = new PaymentModel();
		initPayment(payment);
		model.addAttribute("payment", payment);
		model.addAllAttributes(selectValuesService.getPaymentDictionaries());
		return "payment";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final ContractPackage contractPackage, final Model model,
			final Principal principal) {
		System.out.println("saving subscriber " + contractPackage);
		contractPackageService.save(contractPackage, principal.getName());
		model.addAttribute("contractPackage", contractPackage);
		model.addAllAttributes(selectValuesService.getPackageDictionaries());
		model.addAttribute("uiMessage", "Zapisano pakiet");
		return "package";
	}

	private void initPayment(final PaymentModel payment) {
		payment.getInvoices().add(new InvoicePaymentModel());
	}
}