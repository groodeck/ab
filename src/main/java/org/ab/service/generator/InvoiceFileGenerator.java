package org.ab.service.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.ab.model.InvoiceModel;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactoryImp;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Component
@Transactional
public class InvoiceFileGenerator {

	public List<String> generatePdf(final List<InvoiceModel> invoices) {
		final List<String> filesToPrint = Lists.newArrayList();
		for(final InvoiceModel invoice : invoices){
			final String filePath = generateSingleInvoice(invoice);
			if(shouldBePrinted(invoice)){
				filesToPrint.add(filePath);
			}
		}
		return filesToPrint;
	}

	String generateSingleInvoice(final InvoiceModel invoice) {
		try {
			final Document document = new Document();
			final String fileName = invoice.getInvoiceNumber().replace("/", "_");
			final String outputFilePath =
					String.format("src/main/webapp/resources/download/%s.pdf", fileName);
			final PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(outputFilePath));

			document.open();
			final String source = invoice.getHtmlContent();
			final InputStream in = IOUtils.toInputStream(source, "UTF-8");
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

	private boolean shouldBePrinted(final InvoiceModel invoice) {
		// TODO resolve if subscriber need printed invoice
		return true;
	}
}
