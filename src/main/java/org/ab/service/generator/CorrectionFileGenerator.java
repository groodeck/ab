package org.ab.service.generator;

import org.ab.model.CorrectionModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

@Component
@Transactional
public class CorrectionFileGenerator extends FileGenerator {

	public Optional<String> generatePdf(final CorrectionModel correction) {
		final String filePath = generateSingleDocument(correction.getCorrectionNumber(), correction.getHtmlContent());
		if(shouldBePrinted(correction)){
			return Optional.of(filePath);
		} else {
			return Optional.<String>absent();
		}
	}

	private boolean shouldBePrinted(final CorrectionModel correction) {
		// TODO resolve if subscriber need printed invoice
		return true;
	}
}
