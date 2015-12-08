package org.ab.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.ab.dao.PageInfo;
import org.ab.model.CitiesModel;
import org.ab.model.CityModel;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.CityService;
import org.ab.service.InvoicesService;
import org.ab.ui.ResultPage;
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
public class CitiesController extends AbstractController {

	@Autowired
	private CityService cityService;

	@Autowired
	private InvoicesService invoicesService;

	@Autowired
	private SelectValueService selectValuesService;

	@RequestMapping("/changePage/{newPageNo}")
	public String changePage(final Model model, @PathVariable final String newPageNo, final HttpServletRequest request) {
		final PageInfo pageInfo = updatePage(newPageNo, request);
		model.addAttribute("cities", cityService.getCityList(pageInfo));
		return "cities";
	}

	@Override
	protected TableHeader getModelDefaultHeader() {
		return CityModel.resultTableHeader;
	}

	@RequestMapping
	public String handleInitEntry(final Model model, final HttpServletRequest request) {
		final PageInfo pageInfo = getNewPageInfo(request);
		final ResultPage<CityModel> cities = cityService.getCityList(pageInfo);
		model.addAttribute("cities", cities);
		return "cities";
	}

	@RequestMapping("/save")
	public String handleSaveAction(final CitiesModel citiesModel, final Model model,
			final Principal principal,  final HttpServletRequest request) {
		cityService.updateCities(citiesModel, principal.getName());
		final PageInfo pageInfo = getCurrentPageInfo(request);
		model.addAttribute("uiMessage", "Zapisano miasta");
		model.addAttribute("cities", cityService.getCityList(pageInfo));
		return "cities";
	}

	@RequestMapping("/sort/{sortColumnId}")
	public String handleSortChange(final Model model, @PathVariable final String sortColumnId,
			final HttpServletRequest request) {
		final PageInfo pageInfo = updateSortColumn(sortColumnId, request);
		model.addAttribute("cities", cityService.getCityList(pageInfo));
		return "cities";
	}

	@RequestMapping(value="/saveNewCity/{newCityDesc}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveNewCity(@PathVariable final String newCityDesc) {
		final org.ab.model.CityModel city = cityService.saveNewCity(newCityDesc);
		return new JSONSerializer().serialize(city);
	}
}