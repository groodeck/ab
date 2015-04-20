package org.ab.service.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class FileGenerator {

	public static final String DOWNLOAD_DIR = "src/main/webapp/resources/download/";

	String generateSingleDocument(final String documentNumber, final String documentContent) {
		try {
			final Document document = new Document();
			final String fileName = documentNumber.replace("/", "_");
			final String outputFilePath = String.format(DOWNLOAD_DIR + "%s.pdf", fileName);
			final PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(outputFilePath));

			document.open();
			final InputStream in = IOUtils.toInputStream(documentContent, "UTF-8");
			final class DefaultFontProvider extends FontFactoryImp {
				@Override
				public Font getFont(final String fontName, final String encoding, final boolean embedded,
						final float size, final int style, final BaseColor color, final boolean cached) {
					return super.getFont(BaseFont.HELVETICA, BaseFont.CP1257, embedded, size, style, color, cached);
				}
			}
			final XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			worker.parseXHtml(writer, document, in, null, Charsets.toCharset("UTF-8"), new DefaultFontProvider());

			document.close();
			return outputFilePath;
		} catch (final DocumentException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
