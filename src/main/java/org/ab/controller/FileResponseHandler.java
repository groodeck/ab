package org.ab.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FileResponseHandler {
	private static final Logger log = Logger.getLogger(FileResponseHandler.class.getName());

	public void sendToClient(final String filePath, final HttpServletResponse response) {
		final File file = new File(filePath);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "filename=\"" + file.getName() + "\"");
		try {
			FileUtils.copyFile(file, response.getOutputStream());
		} catch (final IOException e) {
			log.error("File resend error: ", e);
		}
	}
}
