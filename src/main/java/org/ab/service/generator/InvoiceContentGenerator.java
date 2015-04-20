package org.ab.service.generator;

import org.springframework.stereotype.Component;

@Component
public class InvoiceContentGenerator extends ContentGenerator {
	@Override
	protected String getTemplateName() {
		return "invoiceTemplate.ftl";
	}
}
