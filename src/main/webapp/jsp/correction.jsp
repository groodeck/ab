<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
 <html xmlns="http://www.w3.org/1999/xhtml">
 
 <jsp:text>
 	<![CDATA[
 		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script> 
 		<script type="text/javascript">
	 		generateInvoices = function (){
	 			var month = $('#month').val()
	 			var year = $('#year').val()
	 			window.location.href = '/invoices/generate/'+year+'/'+month;
	 		}	
	 		
	 		displayInvoice = function(invoiceId){
	 			var subscribeRequest = $.ajax({
	 				//contentType: "application/x-www-form-urlencoded; charset=utf-8",
				   	url: "/async/getInvoiceContent/" + invoiceId
				});
				subscribeRequest.done(function(invoiceContent)	{
	 				$("#invoiceContentDiv").html(invoiceContent);
				});
	 		}
	 		
	 	</script>
 	]]>
 </jsp:text>
 
 <body > 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Korekta</h2>
   
   <div style="float:left; width: 100;">
   <sf:form>
		<table style="font-family:sans-serif;" >
			<tr>
				<td>Numer kortekty</td>
				<td>
					<sf:input path="${correctionNumber}"/>
				</td>
			</tr>
			<tr>
				<td>Numer korygowanej faktury</td>
				<td>
					<sf:input path="${invoice.number}" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td>Sprzedający</td>
				<td>
					<sf:label path="${seller.name}" /><br/>
					<sf:label path="${seller.addressStreet}" /><br/>
					<sf:label path="${seller.addressCity}" />
				</td>
			</tr>
			<tr>
				<td>Klient</td>
				<td>
					<sf:label path="${buyer.name}" /><br/>
					<sf:label path="${buyer.addressStreet}" /><br/>
					<sf:label path="${buyer.addressCity}" />
				</td>
			</tr>
			<tr>
				<td>Data wystawienia korekty</td>
				<td>
					<custom:date name="createDate" value="${correction.createDate}" />
				</td>
			</tr>
			<tr>
				<td>Data wystawienia faktury</td>
				<td>
					<custom:date name="invoiceCreateDate" identifier="invoiceCreateDate" value="${correction.invoice.createDate}" 
						editable="false"/>
				</td>
			</tr>
			<tr>
				<td>Data płatności</td>
				<td>
					<custom:date name="paymentDate" identifier="paymentDate" value="${correction.paymentDate}"/>
				</td>
			</tr>
		</table>
		<br/>
		
		<c:if test="${not empty serviceRecords}">
			<table border="1" cellspacing="0" cellpadding="2">
				<tr>
					<td>Nazwa usługi</td>
					<td>Ilość</td>
					<td>Okres</td>
					<td>Wartość netto</td>
					<td>VAT</td>
					<td>Wartość brutto</td>
				</tr>
				<c:forEach var="service" items="${serviceRecords}" varStatus="status" >
					<tr>
						<td><c:out value="${service.serviceName}"/></td>
						<td><c:out value="${service.invoiceNumber}"/></td>
						<td><c:out value="${service.buyer.name}"/></td>
						<td><c:out value="${service.settlementPeriodStart} - ${invoice.settlementPeriodEnd}"/></td>
						<td><c:out value="${service.createDate}"/></td>
						<td><c:out value="${invoice.grossAmount}"/></td>
						<td><input type="button" value="Korekta" onClick="window.location.href = '/correction/new/${invoice.invoiceId}'"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		</sf:form>
	</div>
	
</body>
</html>
	
</jsp:root>