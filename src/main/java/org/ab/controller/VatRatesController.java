package org.ab.controller;

import org.ab.model.VatRateFormModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.InvoicesService;
import org.ab.service.VatRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/vatRates")
public class VatRatesController {

	@Autowired
	private VatRateService vatRateService;

	@Autowired
	private InvoicesService invoicesService;

	@Autowired
	private SelectValueService selectValuesService;

	@RequestMapping
	public String handleInitEntry(final Model model) {
		final VatRateFormModel vatRatesModel = new VatRateFormModel();
		vatRatesModel.setRates(vatRateService.getVatRateList());
		model.addAttribute("vatRates", vatRatesModel);
		return "vatRates";
	}

	@RequestMapping(value="/saveNewVatRate/{vatRateDesc}/{vatRateValue}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveNewVatRate(final @PathVariable("vatRateDesc") String newVatRateDesc,
			final @PathVariable("vatRateValue") String newVatRateValue) {
		final org.ab.model.VatRateModel vatRate = vatRateService.saveNewVatRate(newVatRateDesc, newVatRateValue);
		return new JSONSerializer().serialize(vatRate);
	}
}