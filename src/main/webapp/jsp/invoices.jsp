<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
     
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
	 		
	 	</script>
 	]]>
 </jsp:text>
 
 <body > 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Faktury</h2>
   
		<table style="font-family:sans-serif;" >
			<tr>
				<td>
					<sf:form method="get" action="/invoices/search">
						Znajdź fakturę: 
						<input name="searchPhrase" value="${searchPhrase}"/>
						<input type="submit" value="Znajdź"/>
					</sf:form>
				</td>
				<td width="30"/>
				<td>
					<sf:form method="post" commandName="generationParams" action="/invoices/generate">
						Miesiąc: <sf:select path="month" items="${months}" id="month" />
						Rok: <sf:select path="year" items="${years}" id="year" />
						<button>Generuj faktury</button>
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
						<td onclick="editSubscriber(${invoice.invoiceId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="editSubscriber(${invoice.invoiceId})"><c:out value="${invoice.buyer.name}"/></td>
						<td onclick="editSubscriber(${invoice.invoiceId})"><c:out value="${invoice.settlementPeriodStart} - ${invoice.settlementPeriodEnd}"/></td>
						<td onclick="editSubscriber(${invoice.invoiceId})"><c:out value="${invoice.createDate}"/></td>
						<td onclick="editSubscriber(${invoice.invoiceId})"><c:out value="${invoice.grossAmount}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	
		<div style="border: solid;">
			<c:out value="${invoice}" escapeXml="false"/>
		</div>
		
</body>
</html>
	
</jsp:root>