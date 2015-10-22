package org.ab.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.ab.model.dictionary.ReportType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportGeneratorOfType {

	ReportType value();

}
