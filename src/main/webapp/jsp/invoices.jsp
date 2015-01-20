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
   
   <h2>Faktury</h2>
   
   <div style="float:left; width: 50%;">
		<table style="font-family:sans-serif;" >
			<tr>
				<td>
					<sf:form method="post" commandName="generationParams" action="/invoices/generate">
						<table>
						<tr>
							<td>Generuj faktury na: <sf:select path="month" items="${months}" id="month" />
								<sf:select path="year" items="${years}" id="year" /></td>
							<td width="5px"/>
							<td><button>Generuj</button></td>
						</tr>
						</table>
					</sf:form>
				</td>
			</tr>
			<tr>
				<td>
					<hr/>
				</td>
			</tr>
			<tr>
				<td>
					<sf:form method="get" action="/invoices/search">
						<table>
						<tr valign="middle">
							<td>Filtr:</td> 
							<td>OD: <custom:date name="searchDateFrom" identifier="searchDateFrom" 
								value="${searchDateFrom}" additionalAttributes="size='15'"/></td>
							<td>DO: <custom:date name="searchDateTo" identifier="searchDateTo" 
								value="${searchDateTo}" additionalAttributes="size='15'"/></td>
							<td width="10px"/>
							<td><input type="submit" value="Znajdź"/></td>
						</tr>
						</table>
					</sf:form>
				</td>
			</tr>
		</table>
		<br/>
		
		<c:if test="${not empty invoices}">
			<table border="1" cellspacing="0" cellpadding="2">
				<tr>
					<td>lp</td>
					<td>Abonent</td>
					<td>Okres rozliczeniowy</td>
					<td>Data utworzenia</td>
					<td>Wartość faktury</td>
				</tr>
				<c:forEach var="invoice" items="${invoices}" varStatus="status" >
					<tr>
						<td onclick="displayInvoice(${invoice.invoiceId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="displayInvoice(${invoice.invoiceId})"><c:out value="${invoice.buyer.name}"/></td>
						<td onclick="displayInvoice(${invoice.invoiceId})"><c:out value="${invoice.settlementPeriodStart} - ${invoice.settlementPeriodEnd}"/></td>
						<td onclick="displayInvoice(${invoice.invoiceId})"><c:out value="${invoice.createDate}"/></td>
						<td onclick="displayInvoice(${invoice.invoiceId})"><c:out value="${invoice.grossAmount}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
	
	<div id="invoiceContentDiv" style="float:left; width: 50%">
		<c:out value="${invoice}" escapeXml="false"/>
	</div>
	
</body>
</html>
	
</jsp:root>