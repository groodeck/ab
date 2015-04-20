package org.ab.service.generator;

import org.springframework.stereotype.Component;

@Component
public class CorrectionContentGenerator extends ContentGenerator {
	@Override
	protected String getTemplateName() {
		return "correctionTemplate.ftl";
	}
}
