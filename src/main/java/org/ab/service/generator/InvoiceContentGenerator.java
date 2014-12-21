package org.ab.service.generator;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class InvoiceContentGenerator {

	public String generateHtml(final Invoice invoice) {
		final StringWriter out = new StringWriter();
		final Configuration cfg = new Configuration();
		cfg.setObjectWrapper( new DefaultObjectWrapper());
		try {
			final Template temp = cfg.getTemplate( "src/main/java/org/ab/service/generator/invoiceTemplate.ftl" );
			temp.process(invoice, out);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return out.getBuffer().toString();
	}
}
