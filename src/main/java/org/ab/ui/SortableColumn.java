package org.ab.ui;

public class SortableColumn {

	private SortOrder sortOrder;
	private final String columnKey;
	private final String columnName;
	private final String columnDescription;

	public SortableColumn(final String columnKey, final String columnName, final String columnDescription) {
		this.columnKey = columnKey;
		this.columnName = columnName;
		this.columnDescription = columnDescription;
	}

	public void clear() {
		sortOrder = null;
	}

	public String getColumnDescription() {
		return columnDescription;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public String getColumnName() {
		return columnName;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(final SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
