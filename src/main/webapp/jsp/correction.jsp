<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
    
 <html xmlns="http://www.w3.org/1999/xhtml">
 
  <body> 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Korekta</h2>
   
   <sf:form method="post" action="/correction/save" modelAttribute="correction" >
		
		<table style="font-family:sans-serif;" cellspacing="5px" >
			<tr>
				<th align="right"><label>Nr korekty:</label></th>
				<td>
					<sf:input path="correctionNumber" />
				</td>
			</tr>
			<tr>
				<th align="right"><label>Numer korygowanej faktury:</label></th>
				<td>
					<sf:hidden path="invoice.invoiceNumber" />
					<sf:input path="invoice.invoiceNumber" disabled="true"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Okres rozliczeniowy:</label></th>
				<td>
					<sf:hidden path="invoice.settlementPeriodStart" />
					<sf:hidden path="invoice.settlementPeriodEnd" />
					<c:out value="${correction.invoice.settlementPeriodStart} - ${correction.invoice.settlementPeriodEnd}"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Klient:</label></th>
				<td style="border: solid 1px;">
					<sf:hidden path="invoice.subscriber.name" />
					<sf:hidden path="invoice.subscriber.addressStreet" />
					<sf:hidden path="invoice.subscriber.addressCity" />
					<c:out value="${correction.invoice.subscriber.name}" /><br/>
					<c:out value="${correction.invoice.subscriber.addressStreet}" /><br/>
					<c:out value="${correction.invoice.subscriber.addressCity}" />
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data wystawienia korekty:</label></th>
				<td>
					<sf:hidden path="createDate" />
					<custom:date name="createDate" identifier="createDate" value="${correction.createDate}" />
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data wystawienia faktury:</label></th>
				<td>
					<sf:hidden path="invoice.createDate" />
					<custom:date name="invoiceCreateDate" identifier="invoiceCreateDate" value="${correction.invoice.createDate}" 
						editable="false"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data dostarczenia:</label></th>
				<td>
					<sf:hidden path="receiveDate" />
					<custom:date name="receiveDate" identifier="receiveDate" value="${correction.receiveDate}"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data płatności:</label></th>
				<td>
					<sf:hidden path="paymentDate" />
					<custom:date name="paymentDate" identifier="paymentDate" value="${correction.paymentDate}"/>
				</td>
			</tr>
		</table>
		<button> - </button>
		<br/>
		
		<!-- <c:if test="${not empty correction.serviceRecords}">
		
			<table border="1" cellpadding="2">
				<tr>
					<td>Nazwa usługi</td>
					<td/>
					<td>Ilość</td>
					<td>Cena netto</td>
					<td>Wartość netto</td>
					<td>Stawka VAT</td>
					<td>Wartość VAT</td>
					<td>Wartość brutto</td>
					<td/>
				</tr>
				<c:forEach var="service" items="${correction.serviceRecords}" varStatus="status" >
					<tr>
						<td><c:out value="${service.invoiceRecord.serviceName}"/></td>
						<td>Przed korektą</td>
						<td><c:out value="${service.invoiceRecord.quantity}"/></td>
						<td><c:out value="${service.invoiceRecord.netPrice}"/></td>
						<td><c:out value="${service.invoiceRecord.netAmount}"/></td>
						<td><c:out value="${service.invoiceRecord.vatRate}"/></td>
						<td><c:out value="${service.invoiceRecord.vatAmount}"/></td>
						<td><c:out value="${service.invoiceRecord.grossAmount}"/></td>
						<td rowspan="2"><input type="button" value=" - " onClick="window.location.href = '/correction/delRow/${status.index}'" style="width: 35px;"/>
						<button> - </button></td>
					</tr>
					<tr>
						<td rowspan="2"><input name="serviceRecords[${status.index}].serviceName" value="${service.serviceName}" /></td>
						<td>Po korekcie</td>
						<td><input name="serviceRecords[${status.index}].quantity" value="${service.quantity}"/></td>
						<td><input name="serviceRecords[${status.index}].netPrice" value="${service.netPrice}"/></td>
						<td><input name="serviceRecords[${status.index}].netAmount" value="${service.netAmount}" disabled="disabled"/></td>
						<td><input name="serviceRecords[${status.index}].vatRate" value="${service.vatRate}"/></td>
						<td><input name="serviceRecords[${status.index}].vatAmount" value="${service.vatAmount}" disabled="disabled"/></td>
						<td><input name="serviceRecords[${status.index}].grossAmount" value="${service.grossAmount}" disabled="disabled"/></td>
					</tr>
					<tr>
						
						<td>Korekta</td>
						<td><input name="serviceRecords[${status.index}].quantityDiff" value="${service.quantityDiff}" disabled="disabled"/></td>
						<td><input name="serviceRecords[${status.index}].netPriceDiff" value="${service.netPriceDiff}" disabled="disabled"/></td>
						<td><input name="serviceRecords[${status.index}].netAmountDiff" value="${service.netAmountDiff}" disabled="disabled"/></td>
						<td><input name="serviceRecords[${status.index}].vatRate" value="${service.vatRate}" disabled="disabled"/></td>
						<td><input name="serviceRecords[${status.index}].vatAmountDiff" value="${service.vatAmountDiff}" disabled="disabled"/></td>
						<td><input name="serviceRecords[${status.index}].grossAmountDiff" value="${service.grossAmountDiff}" disabled="disabled"/></td>
						<td>
							<c:if test="${status.last}">
								<input type="button" value=" + " onClick="window.location.href = '/correction/addRow'" style="width: 35px;"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3"/>
					<td>RAZEM</td>
					<td><c:out value="${correction.netAmount}"/></td>
					<td/>
					<td><c:out value="${correction.vatAmount}"/></td>
					<td><c:out value="${correction.grossAmount}"/></td>
				</tr>
			</table>
		</c:if> -->
		
		</sf:form>
	
</body>
</html>
	
</jsp:root>