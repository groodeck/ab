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
	 		alert('jestem');
	 			var month = $('#month').val()
	 			var year = $('#year').val()
	 			window.location.href = '/invoices/generate/'+year+'/'+month;
	 		}	
	 		
	 		
	 	</script>
 	]]>
 </jsp:text>
 
 <body> 
   
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
					<td>Data podpisania umowy</td>
					<td>Abonent</td>
					<td>Adres</td>
					<td>Miejscowość</td>
					<td>Telefon</td>
					<td>Data zakończenia umowy</td>
					<td>Przedstawiciel handlowy</td>
				</tr>
				<c:forEach var="subscriber" items="${subscribers}" varStatus="status" >
					<tr>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.contractSignDate}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.effectiveName}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.mainAddress.streetDetails}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.mainAddress.city}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.phoneList}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.contractEndDate}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.user}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	
	
</body>
</html>
	
</jsp:root>