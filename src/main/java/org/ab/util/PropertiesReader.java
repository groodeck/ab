package org.ab.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public static String getProperty(final String propertyKey){
		return systemProperties.getProperty(propertyKey);
	}

	private static Properties loadProperties(final String fileName) {
		final Properties prop = new Properties();
		InputStream input=null;
		try {
			final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			input = classLoader.getResourceAsStream(SYSTEM_PROPERTIES);
			prop.load(input);
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	private static final String SYSTEM_PROPERTIES = "system.properties";

	private static final Properties systemProperties = loadProperties(SYSTEM_PROPERTIES);
}
