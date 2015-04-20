package org.ab.service.generator;

import java.util.List;

import org.ab.model.InvoiceModel;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InvoiceFileGenerator extends FileGenerator {

	public List<String> generatePdf(final List<InvoiceModel> invoices) {
		final List<String> filesToPrint = Lists.newArrayList();
		for(final InvoiceModel invoice : invoices){
			final String filePath = generateSingleDocument(invoice.getInvoiceNumber(), invoice.getHtmlContent());
			if(shouldBePrinted(invoice)){
				filesToPrint.add(filePath);
			}
		}
		return filesToPrint;
	}

	private boolean shouldBePrinted(final InvoiceModel invoice) {
		// TODO resolve if subscriber need printed invoice
		return true;
	}
}
