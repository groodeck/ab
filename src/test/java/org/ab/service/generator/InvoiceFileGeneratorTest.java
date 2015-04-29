package org.ab.service.generator;

import org.ab.model.InvoiceModel;
import org.junit.Test;

public class InvoiceFileGeneratorTest {

	private final InvoiceFileGenerator service = new InvoiceFileGenerator();

	@Test
	public void shouldPrintPdf(){
		// given
		final InvoiceModel invoice = new InvoiceModel.Builder()
		.withInvoiceNumber("FVAT/00001/02/2000")
		.withHtmlContent(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> " +
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>" +
						"<html>"+
						"	<body>"+
						"		<table cellspacing='0' style='font-size: 11px; font-family: arial;'>"+
						"			<tr style='text-align:center; vertical-align: middle; font-size: 15px; font-weight: bold;'>"+
						"				<td width='230' height='60'></td>"+
						"				<td colspan='6' width='200'>FAKTURA VAT</td>"+
						"				<td colspan='4' width='220'>${invoiceNumber}</td>"+
						"			</tr>"+
						"		</table>"+
						"	</body>"+
				"</html")
				.build();

		// when
		service.createFile(invoice.getInvoiceNumber(), invoice.getHtmlContent());

		// then
	}
}
