package org.ab.controller;

import org.ab.model.CorrectionModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/correction")
public class CorrectionController {

	@Autowired
	private CorrectionService correctionService;

	@Autowired
	private SelectValueService selectValuesService;

	@RequestMapping("/new/{invoiceId}")
	public String handleNewCorrection(@PathVariable final int invoiceId, final Model model) {
		final CorrectionModel correction = correctionService.prepareCorrection(invoiceId);

		model.addAllAttributes(selectValuesService.getCorrectionDictionaries());

		model.addAttribute("correction", correction);
		return "correction";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final CorrectionModel correction, final Model model) {
		model.addAttribute("correction", correction);
		return "correction";
	}

}