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

//		final List<Invoice> invoices = Lists.newArrayList(invoice);
//		final Map<String, Object> root = new HashMap<String, Object>();
//		root.put( "invoices", invoices );
		final StringWriter out = new StringWriter();

		final Configuration cfg = new Configuration();
		cfg.setObjectWrapper( new DefaultObjectWrapper());
		try {
			final Template temp = cfg.getTemplate( "src/main/java/org/ab/service/generator/invoiceTemplate.ftl" );
			temp.process(invoice, out);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}

		final String html = out.getBuffer().toString();
//		System.out.println( html );

//		final StringBuilder sb = new StringBuilder();
//			.append("<table border='1'>")
//			.append("<tr>")
//			.append("	<td rowspan='3' colspan='3' width='250'>")
//
//			.append("	</td>
//			.append("	<td colspan='7' width='200'><h2>FAKTURA VAT</h2></td>
//			.append("	<td colspan='4' width='150'>1/2/2014</td>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='7'>Data wystawienia</td>
//			.append("	<td colspan='4'>ORYGINA£</td>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='7'>Data sprzeda¿y</td>
//			.append("	<td colspan='4'/>
//			.append("</tr>
//
//			.append("<tr>
//			.append("	<td colspan='3' align="center">pieczêæ sprzedawcy</td>
//			.append("	<td colspan='11'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='7'>Sprzedawca</td>
//			.append("	<td colspan='7'>Nabywca</td>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='7' rowspan='3'>
//			.append("		jawor net<br/>
//			.append("		00-100 Sierpc<br/>
//			.append("		£owicka 8/15
//			.append("	</td>
//			.append("	<td colspan='7' rowspan='3'>
//			.append("		Jan Kowalski<br/>
//			.append("		00-100 Sierpc<br/>
//			.append("		Narutowicza 2
//			.append("	</td>
//			.append("</tr>
//			.append("<tr/>
//			.append("<tr/>
//			.append("<tr>
//			.append("	<td colspan='7'>NIP:</td>
//			.append("	<td colspan='7'>NIP:</td>
//			.append("</tr>
//
//			.append("<tr>
//			.append("	<td colspan='7' height='30'>Nr rachunku:</td>
//			.append("	<td colspan='7'>Sposób zap³aty:</td>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='14' height='5'/>
//			.append("</tr>
//
//			.append("<tr>
//			.append("	<td rowspan='3'>Lp.</td>
//			.append("	<td rowspan='3'>Nazwa towaru lub us³ugi</td>
//			.append("	<td rowspan='3'>PKWiU</td>
//			.append("	<td rowspan='3'>J.m.</td>
//			.append("	<td rowspan='3'>Iloœæ</td>
//			.append("	<td rowspan='2' colspan='2'>Cena jednostkowa bez podatku</td>
//			.append("	<td rowspan='2' colspan='2'>Wartoœæ netto</td>
//			.append("	<td colspan='3'>Podatek</td>
//			.append("	<td rowspan='2' colspan='2'>Wartoœæ brutto</td>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td>%</td>
//			.append("	<td colspan='2'>kwota</td>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td>z³</td>
//			.append("	<td>gr</td>
//			.append("	<td>z³</td>
//			.append("	<td>gr</td>
//			.append("	<td/>
//			.append("	<td>z³</td>
//			.append("	<td>gr</td>
//			.append("	<td>z³</td>
//			.append("	<td>gr</td>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td>1</td>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("	<td/>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='5'/>
//			.append("	<td colspan='2'>RAZEM</td>
//			.append("	<td colspan='2'/>
//			.append("	<td>x</td>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='5'/>
//			.append("	<td colspan='2' rowspan='5'>W TYM</td>
//			.append("	<td colspan='2'/>
//			.append("	<td>zw</td>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='5'/>
//			.append("	<td colspan='2'/>
//			.append("	<td>23</td>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='5'>S³ownie:</td>
//			.append("	<td colspan='2'/>
//			.append("	<td>8</td>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='5' rowspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("	<td>5</td>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='2'/>
//			.append("	<td>0</td>
//			.append("	<td colspan='2'/>
//			.append("	<td colspan='2'/>
//			.append("</tr>
//
//			.append("<tr>
//			.append("	<td colspan='14' height='5'/>
//			.append("</tr>
//
//			.append("<tr>
//			.append("	<td colspan='5' rowspan='2' height='40'>Razem do zap³aty:</td>
//			.append("	<td colspan='9' rowspan='2'></td>
//			.append("</tr>
//			.append("<tr/>
//
//			.append("<tr>
//			.append("	<td colspan='14' height='5'/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td colspan='14' rowspan='2' height='40' valign="top">Uwagi:</td>
//			.append("</tr>
//			.append("<tr/>
//
//			.append("<tr>
//			.append("	<td colspan='14' height='5'/>
//			.append("</tr>
//
//			.append("<tr>
//			.append("	<td/>
//			.append("	<td colspan='2' rowspan='4' height='80' />
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td colspan='7' rowspan='4' height='80' />
//			.append("	<td/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("</tr>
//			.append("<tr>
//			.append("	<td/>
//			.append("	<td colspan='2' style="font-size: 10px">Podpis imienny osoby upowa¿nionej do odbioru faktur VAT</td>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td/>
//			.append("	<td colspan='7' style="font-size: 10px">Podpis imienny osoby upowa¿nionej do wystawienia faktury VAT</td>
//			.append("	<td/>
//			.append("</tr>
//
//		.append("</table>"
		return html;
	}

}
