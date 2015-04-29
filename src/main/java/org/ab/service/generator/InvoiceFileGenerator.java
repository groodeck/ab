package org.ab.service.generator;

import org.ab.model.InvoiceModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

@Component
@Transactional
public class InvoiceFileGenerator extends FileGenerator {

	public Optional<String> generatePdf(final InvoiceModel invoice) {
		final String filePath = createFile(invoice.getInvoiceNumber(), invoice.getHtmlContent());
		if(shouldBePrinted(invoice)){
			return Optional.of(filePath);
		} else {
			return Optional.<String>absent();
		}
	}

	private boolean shouldBePrinted(final InvoiceModel invoice) {
		// TODO resolve if subscriber need printed invoice
		return true;
	}

}
