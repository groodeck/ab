package org.ab.controller;

import javax.servlet.http.HttpServletRequest;

import org.ab.dao.PageInfo;
import org.ab.model.ContractPackage;
import org.ab.model.dictionary.SelectValueService;
import org.ab.service.ContractPackageService;
import org.ab.service.InvoicesService;
import org.ab.ui.ResultPage;
import org.ab.ui.TableHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/packages")
public class ContractPackagesController extends AbstractController {

	private static final String SHOW_ALL = "showAll";

	@Autowired
	private ContractPackageService contractPackageService;

	@Autowired
	private InvoicesService invoicesService;

	@Autowired
	private SelectValueService selectValuesService;

	@RequestMapping("/changePage/{newPageNo}")
	public String changePage(final Model model, @PathVariable final String newPageNo, final HttpServletRequest request) {
		final boolean showAll = getShowAllAttribute(request);
		final PageInfo pageInfo = updatePage(newPageNo, request);
		final ResultPage<ContractPackage> packageList = contractPackageService.getAllPackages(showAll, pageInfo);
		model.addAttribute("contractPackages", packageList);
		return "packages";
	}

	@Override
	protected TableHeader getModelDefaultHeader() {
		return ContractPackage.resultTableHeader;
	}

	private boolean getShowAllAttribute(final HttpServletRequest request) {
		final Boolean showAll = (Boolean)request.getSession().getAttribute(SHOW_ALL);
		return showAll != null && showAll.booleanValue();
	}

	@RequestMapping
	public String handleInitEntry(final Model model, final HttpServletRequest request) {
		final boolean showAll = false;
		final PageInfo pageInfo = getNewPageInfo(request);
		final ResultPage<ContractPackage> packageList = contractPackageService.getAllPackages(showAll, pageInfo);
		model.addAttribute("contractPackages", packageList);
		request.getSession().setAttribute(SHOW_ALL, showAll);
		return "packages";
	}

	@RequestMapping("/showAll")
	public String handleShowAllAction(final Model model, final HttpServletRequest request) {
		final boolean showAll = true;
		final PageInfo pageInfo = getCurrentPageInfo(request);
		model.addAttribute("contractPackages", contractPackageService.getAllPackages(showAll, pageInfo));
		request.getSession().setAttribute(SHOW_ALL, showAll);
		return "packages";
	}

	@RequestMapping("/sort/{sortColumnId}")
	public String handleSortChange(final Model model, @PathVariable final String sortColumnId,
			final HttpServletRequest request) {
		final boolean showAll = getShowAllAttribute(request);
		final PageInfo pageInfo = updateSortColumn(sortColumnId, request);
		final ResultPage<ContractPackage> packageList = contractPackageService.getAllPackages(showAll, pageInfo);
		model.addAttribute("contractPackages", packageList);
		return "packages";
	}
}