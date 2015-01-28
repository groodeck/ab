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
	 		editContractPackage = function (packageId){
	 			window.location.href = '/package/edit/'+packageId;
	 		}	
	 	</script>
 	]]>
 </jsp:text>
 
 <body> 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Wpłaty</h2>
		
		<a href="/payment/new"><button>Dodaj wpłatę</button></a>
   
		<c:if test="${not empty payments}">
			<br/><br/>
			<table border="1" cellspacing="0" cellpadding="2">
				<tr>
					<td>lp</td>
					<td>Klient</td>
					<td>Kwota wpłaty</td>
					<td>Rozliczone faktury</td>
				</tr>
				<c:forEach var="payment" items="${payments}" varStatus="status" >
					<tr>
						<td onclick="editContractPackage(${payment.paymentId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="editContractPackage(${payment.paymentId})"><c:out value="${payment.subscriber.effectiveName}"/></td>
						<td onclick="editContractPackage(${payment.paymentId})"><c:out value="${payment.paymentAmount}"/></td>
						<td onclick="editContractPackage(${payment.paymentId})">
							<c:forEach var="invoice" items="${payment.invoices}" >
								<c:out value="${invoice.invoiceNumber}"/>
								<br/>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	
</body>
</html>
	
</jsp:root>