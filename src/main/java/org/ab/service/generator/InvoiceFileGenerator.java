package org.ab.service.generator;

import java.util.List;

import org.ab.model.InvoiceModel;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

@Component
@Transactional
public class InvoiceFileGenerator extends FileGenerator {

	public Optional<String> generatePdf(final InvoiceModel invoice) {
		final String filePath = createFile(invoice.getInvoiceNumber(), invoice.getHtmlContent());
		if(shouldBePrinted(invoice)){
			return Optional.fromNullable(filePath);
		} else {
			return Optional.<String>absent();
		}
	}

	public List<String> generatePdf(final List<InvoiceModel> invoices) {
		final List<String> resultFilesPaths = Lists.newArrayList();
		for(final InvoiceModel invoice : invoices){
			final Optional<String> generatedFilePath = generatePdf(invoice);
			if(generatedFilePath.isPresent()){
				resultFilesPaths.add(generatedFilePath.get());
			}
		}
		return resultFilesPaths;
	}

	private boolean shouldBePrinted(final InvoiceModel invoice) {
		//		 TODO resolve if subscriber need printed invoicey
		//	 	2. print only invoices for specific subscriber - email not defined;
		return true;
	}

}
