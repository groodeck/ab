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
		<c:if test="${not empty correction.serviceRecords}">
				<c:forEach var="service" items="${correction.serviceRecords}" varStatus="status" >
					<tr>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.serviceName" />
							<c:out value="${service.invoiceRecord.serviceName}"/>
						</td>
						<td>Przed korektą</td>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.quantity" />
							<c:out value="${service.invoiceRecord.quantity}"/>
						</td>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.netPrice" />
							<c:out value="${service.invoiceRecord.netPrice}"/>
						</td>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.netAmount" />
							<c:out value="${service.invoiceRecord.netAmount}"/>
						</td>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.vatRate" />
							<c:out value="${service.invoiceRecord.vatRate}"/>
						</td>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.vatAmount" />
							<c:out value="${service.invoiceRecord.vatAmount}"/>
						</td>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.grossAmount" />
							<c:out value="${service.invoiceRecord.grossAmount}"/>
						</td>
						<td rowspan="2"><input type="button" value=" - " onClick="window.location.href = '/correction/delRow/${status.index}'" style="width: 35px;"/>
						<button> - </button></td>
					</tr>
					<tr>
						<td rowspan="2"><sf:input path="serviceRecords[${status.index}].serviceName" /></td>
						<td>Po korekcie</td>
						<td><sf:input path="serviceRecords[${status.index}].quantity" /></td>
						<td><sf:input path="serviceRecords[${status.index}].netPrice" /></td>
						<td><sf:input path="serviceRecords[${status.index}].netAmount"  disabled="true"/></td>
						<td><sf:input path="serviceRecords[${status.index}].vatRate" /></td>
						<td><sf:input path="serviceRecords[${status.index}].vatAmount" disabled="true"/></td>
						<td><sf:input path="serviceRecords[${status.index}].grossAmount" disabled="true"/></td>
					</tr>
					<tr>
						
						<td>Korekta</td>
						<td><sf:input path="serviceRecords[${status.index}].quantityDiff" disabled="true"/></td>
						<td><sf:input path="serviceRecords[${status.index}].netPriceDiff" disabled="true"/></td>
						<td><sf:input path="serviceRecords[${status.index}].netAmountDiff" disabled="true"/></td>
						<td><sf:input path="serviceRecords[${status.index}].vatRate" disabled="true"/></td>
						<td><sf:input path="serviceRecords[${status.index}].vatAmountDiff" disabled="true"/></td>
						<td><sf:input path="serviceRecords[${status.index}].grossAmountDiff" disabled="true"/></td>
						<td>
							<c:if test="${status.last}">
								<input type="button" value=" + " onClick="window.location.href = '/correction/addRow'" style="width: 35px;"/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
		</c:if>
				<tr>
					<td colspan="3"/>
					<td>Przed korektą</td>
					<td>
						<sf:hidden path="netAmount" />
						<c:out value="${correction.invoice.netAmount}"/>
					</td>
					<td>
						<sf:hidden path="vatAmount" />
						<c:out value="${correction.invoice.vatAmount}"/>
					</td>
					<td>
						<sf:hidden path="grossAmount" />
						<c:out value="${correction.invoice.grossAmount}"/>
					</td>
				</tr>
				<tr>
					<td colspan="3"/>
					<td>Po korekcie</td>
					<td>
						<sf:input path="netAmount" disabled="true"/>
					</td>
					<td>
						<sf:input path="vatAmount" disabled="true"/>
					</td>	
					<td>
						<sf:input path="grossAmount" disabled="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="3"/>
					<td>Korekta</td>
					<td>
						<sf:input path="netAmountDiff" disabled="true"/>
					</td>
					<td>
						<sf:input path="vatAmountDiff" disabled="true"/>
					</td>
					<td>
						<sf:input path="grossAmountDiff" disabled="true"/>
					</td>
				</tr>
			</table>
		
		</sf:form>
	
</body>
</html>
	
</jsp:root>
