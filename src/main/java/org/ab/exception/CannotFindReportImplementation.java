package org.ab.exception;

public class CannotFindReportImplementation extends Exception {

	private static final long serialVersionUID = -5673417239062170262L;

	private final String reportTypeName;

	public CannotFindReportImplementation(final String forType) {
		reportTypeName = forType;
	}

	public String getReportTypeName() {
		return reportTypeName;
	}

}
