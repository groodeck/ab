package org.ab.controller;

import java.security.Principal;

import org.ab.model.CitiesModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.CityService;
import org.ab.service.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/cities")
public class CitiesController {

	@Autowired
	private CityService cityService;

	@Autowired
	private InvoicesService invoicesService;

	@Autowired
	private SelectValueService selectValuesService;

	@RequestMapping
	public String handleInitEntry(final Model model) {
		final CitiesModel citiesModel = new CitiesModel();
		citiesModel.setCities(cityService.getCityList());
		model.addAttribute("cities", citiesModel);
		return "cities";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final CitiesModel citiesModel, final Model model,
			final Principal principal) {
		cityService.updateCities(citiesModel, principal.getName());
		model.addAttribute("uiMessage", "Zapisano miasta");
		return handleInitEntry(model);
	}

	@RequestMapping(value="/saveNewCity/{newCityDesc}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveNewCity(@PathVariable final String newCityDesc) {
		final org.ab.model.CityModel city = cityService.saveNewCity(newCityDesc);
		return new JSONSerializer().serialize(city);
	}
}