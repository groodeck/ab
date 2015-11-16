package org.ab.controller;

import static org.ab.ui.TableHeader.TABLE_HEADER;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.ab.model.CitiesModel;
import org.ab.model.CityModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.CityService;
import org.ab.service.InvoicesService;
import org.ab.ui.SortableColumn;
import org.ab.ui.TableHeader;
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
	public String handleInitEntry(final Model model, final HttpServletRequest request) {
		final CitiesModel citiesModel = new CitiesModel();
		citiesModel.setCities(cityService.getCityList(null));
		model.addAttribute("cities", citiesModel);
		request.getSession().setAttribute(TABLE_HEADER, CityModel.resultTableHeader);
		return "cities";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final CitiesModel citiesModel, final Model model,
			final Principal principal,  final HttpServletRequest request) {
		cityService.updateCities(citiesModel, principal.getName());
		model.addAttribute("uiMessage", "Zapisano miasta");
		model.addAttribute("cities", citiesModel);
		return "cities";
	}

	@RequestMapping("/sort/{sortColumnId}")
	public String handleSortChange(final Model model, @PathVariable final String sortColumnId,
			final HttpServletRequest request) {
		final CitiesModel citiesModel = new CitiesModel();
		final TableHeader header = (TableHeader)request.getSession().getAttribute(TABLE_HEADER);
		final SortableColumn sortColumn = header.updateSortColumn(request, sortColumnId);
		citiesModel.setCities(cityService.getCityList(sortColumn));
		model.addAttribute("cities", citiesModel);
		return "cities";
	}

	@RequestMapping(value="/saveNewCity/{newCityDesc}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveNewCity(@PathVariable final String newCityDesc) {
		final org.ab.model.CityModel city = cityService.saveNewCity(newCityDesc);
		return new JSONSerializer().serialize(city);
	}
}