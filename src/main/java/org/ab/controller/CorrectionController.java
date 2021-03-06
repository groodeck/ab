package org.ab.controller;

import org.ab.model.CorrectionModel;
import org.ab.model.InvoiceGenerationParams;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.CorrectionService;
import org.ab.service.generator.CorrectionServiceRecord;
import org.joda.time.LocalDate;
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

	@RequestMapping("/addRow")
	public String handleAddRowAction(final CorrectionModel correction, final Model model) {
		correction.getServiceRecords().add(new CorrectionServiceRecord.Builder().build());
		model.addAllAttributes(selectValuesService.getCorrectionDictionaries(
				correction.getInvoice().getSubscriberIdn()));
		model.addAttribute("correction", correction);
		return "correction";
	}

	@RequestMapping("/delRow/{rowNumber}")
	public String handleDelRowAction(@PathVariable final int rowNumber, final CorrectionModel correction, final Model model) {
		correction.getServiceRecords().remove(rowNumber);
		correctionService.calculateCorrectionSum(correction);
		model.addAllAttributes(selectValuesService.getCorrectionDictionaries(
				correction.getInvoice().getSubscriberIdn()));
		model.addAttribute("correction", correction);
		return "correction";
	}

	@RequestMapping("/new/{invoiceId}")
	public String handleNewCorrection(@PathVariable final int invoiceId, final Model model) {
		final CorrectionModel correction = correctionService.prepareCorrection(invoiceId);
		model.addAllAttributes(selectValuesService.getCorrectionDictionaries(
				correction.getInvoice().getSubscriberIdn()));
		model.addAttribute("correction", correction);
		return "correction";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final CorrectionModel correction, final Model model) {
		correctionService.save(correction);
		model.addAttribute("uiMessage", "Zapisano korekt�");
		model.addAttribute("generationParams", new InvoiceGenerationParams(LocalDate.now()));
		model.addAllAttributes(selectValuesService.getInvoicesDictionaries());
		return "invoices";
	}

}