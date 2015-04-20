package org.ab.service.generator;

import java.io.IOException;
import java.io.StringWriter;

import org.ab.model.PrintableContent;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class ContentGenerator {

	public String generateHtml(final PrintableContent model) {
		final StringWriter out = new StringWriter();
		final Configuration cfg = new Configuration();
		cfg.setDateFormat("dd/MM/yyyy");
		cfg.setObjectWrapper( new DefaultObjectWrapper());
		try {
			final Template temp = cfg.getTemplate( "src/main/java/org/ab/service/generator/" + getTemplateName() );
			temp.process(model, out);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return out.getBuffer().toString();
	}

	protected abstract String getTemplateName();
}
