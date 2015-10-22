package org.ab.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ab.entity.Subscriber;
import org.ab.util.PropertiesReader;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractGenerator {

	private static final String RESOURCE_DIR = PropertiesReader.getProperty("system.resourceDir");

	private static final String CONTRACT_TEMPLATE_PATH = RESOURCE_DIR + "template/umowa.docx";
	private static final String CONTRACT_OUTPUT_TEMPLATE_PATH = RESOURCE_DIR + "download/Umowa.%s.docx";

	@Autowired
	private SubscriberContractDetailsReader subscriberDetailsReader;

	public String generate(final Subscriber subscriber) {
		try {
			final Map<String, String> replacements = subscriberDetailsReader.getContractDetails(subscriber);
			XWPFDocument doc = new XWPFDocument(new FileInputStream(CONTRACT_TEMPLATE_PATH));
			doc = replacePlaceholders(doc, replacements);
			final String contractFilePath = String.format(CONTRACT_OUTPUT_TEMPLATE_PATH, subscriber.getSubscriberIdn());
			saveContractFile(contractFilePath, doc);
			return contractFilePath;
		}
		catch(final IOException e){
			e.printStackTrace();
			return null;
		}
	}

	private void replaceAllBodyElements(final List<IBodyElement> bodyElements, final String placeHolder, final String replaceText){
		for (final IBodyElement bodyElement : bodyElements) {
			if (bodyElement.getElementType().compareTo(BodyElementType.PARAGRAPH) == 0) {
				replaceParagraph((XWPFParagraph) bodyElement, placeHolder, replaceText);
			}
			if (bodyElement.getElementType().compareTo(BodyElementType.TABLE) == 0) {
				replaceTable((XWPFTable) bodyElement, placeHolder, replaceText);
			}
		}
	}

	private void replaceParagraph(final XWPFParagraph paragraph, final String placeHolder, final String replaceText) {
		for (final XWPFRun r : paragraph.getRuns()) {
			String text = r.getText(r.getTextPosition());
			if (text != null && text.contains(placeHolder)) {
				text = text.replace(placeHolder, replaceText);
				r.setText(text, 0);
			}
		}
	}

	private XWPFDocument replacePlaceholders(final XWPFDocument doc, final Map<String, String> replacements) {
		XWPFDocument result = doc;
		for(final Entry<String, String> entry : replacements.entrySet()){
			result = replacePOI(doc, entry.getKey(), entry.getValue());
		}
		return result;
	}

	public XWPFDocument replacePOI(final XWPFDocument doc, final String placeHolder, final String replaceText){
		// REPLACE ALL HEADERS
		for (final XWPFHeader header : doc.getHeaderList()) {
			replaceAllBodyElements(header.getBodyElements(), placeHolder, replaceText);
		}
		// REPLACE BODY
		replaceAllBodyElements(doc.getBodyElements(), placeHolder, replaceText);
		return doc;
	}

	private void replaceTable(final XWPFTable table, final String placeHolder, final String replaceText) {
		for (final XWPFTableRow row : table.getRows()) {
			for (final XWPFTableCell cell : row.getTableCells()) {
				for (final IBodyElement bodyElement : cell.getBodyElements()) {
					if (bodyElement.getElementType().compareTo(BodyElementType.PARAGRAPH) == 0) {
						replaceParagraph((XWPFParagraph) bodyElement, placeHolder, replaceText);
					}
					if (bodyElement.getElementType().compareTo(BodyElementType.TABLE) == 0) {
						replaceTable((XWPFTable) bodyElement, placeHolder, replaceText);
					}
				}
			}
		}
	}

	private void saveContractFile(final String filePath, final XWPFDocument doc) throws FileNotFoundException, IOException{
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(filePath);
			doc.write(out);
		}
		finally{
			out.close();
		}
	}
}
