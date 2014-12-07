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
	 		editSubscriber = function (subscriberId){
	 			window.location.href = '/subscriber/edit/'+subscriberId;
	 		}	
	 		
	 		
	 	</script>
 	]]>
 </jsp:text>
 
 <body> 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Abonenci</h2>
   
		<table style="font-family:sans-serif;" >
			<tr>
				<td>
					<sf:form method="get" action="/subscribers/search">
						Znajdź abonenta: 
						<input name="searchPhrase" value="${searchPhrase}"/>
						<input type="submit" value="Znajdź"/>
					</sf:form>
				</td>
				<td width="20"/>
				<td>
					<a href="/subscriber/new"><button>+ Dodaj</button></a>
				</td>
			</tr>
		</table>
		<br/>
		
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
					<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${status.index}"/></td>
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
	
	
</body>
</html>
	
</jsp:root>