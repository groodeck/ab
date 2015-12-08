package org.ab.controller;

import javax.servlet.http.HttpServletRequest;

import org.ab.dao.PageInfo;
import org.ab.ui.TableHeader;

public abstract class AbstractController {

	public static final String TABLE_HEADER = "tableHeader";

	protected PageInfo getCurrentPageInfo(final HttpServletRequest request) {
		final TableHeader tableHeader = getOrCreateTableHeader(request);
		return tableHeader.getPageInfo();
	}

	protected abstract TableHeader getModelDefaultHeader();

	protected PageInfo getNewPageInfo(final HttpServletRequest request) {
		final TableHeader tableHeader = getModelDefaultHeader();
		storeTableHeader(request, tableHeader);
		return tableHeader.getPageInfo();
	}

	public TableHeader getOrCreateTableHeader(final HttpServletRequest request) {
		TableHeader tableHeader = (TableHeader)request.getSession().getAttribute(TABLE_HEADER);
		if(tableHeader == null){
			tableHeader = getModelDefaultHeader();
			storeTableHeader(request, tableHeader);
		}
		return tableHeader;
	}

	private void storeTableHeader(final HttpServletRequest request,
			final TableHeader tableHeader) {
		request.getSession().setAttribute(TABLE_HEADER, tableHeader);
	}

	protected PageInfo updatePage(final String newPageNo, final HttpServletRequest request) {
		final TableHeader tableHeader = getOrCreateTableHeader(request);
		return tableHeader.updatePageNo(request, newPageNo);
	}

	protected PageInfo updateSortColumn(final String sortColumnId, final HttpServletRequest request) {
		final TableHeader tableHeader = getOrCreateTableHeader(request);
		return tableHeader.updateSortColumn(sortColumnId);
	}
}
