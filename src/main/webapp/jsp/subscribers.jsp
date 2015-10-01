<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
     
 <html xmlns="http://www.w3.org/1999/xhtml">
 
 <jsp:text>
 	<![CDATA[
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
					<sf:form method="get" action="/subscribers/search" cssStyle="margin: 0 0 0px;">
						Znajdź abonenta: 
						<input name="searchPhrase" value="${searchPhrase}"/>
						<input type="checkbox" name="showAll" value="true" />Pokaż nieaktywne
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
		
		<c:if test="${not empty subscribers}">
			<table border="1" cellspacing="0" cellpadding="2" class="borderedTable">
				<tr class="tableHeader">
					<td>lp</td>
					<td width="60px">Nr ab.</td>
					<td>Typ klienta</td>
					<td>Abonent</td>
					<td>Adres</td>
					<td>Adres św. usł.</td>
					<td>Miejscowość</td>
					<td>Pakiet</td>
					<td width="100px">Data podpisania umowy</td>
					<td width="100px">Data zakończenia umowy</td>
					<td width="200px">Uwagi</td>
				</tr>
				<c:forEach var="subscriber" items="${subscribers}" varStatus="status" >
					<tr>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.subscriberIdn}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.clientTypeDesc}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.effectiveName}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.mainAddress.streetDetails}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.serviceAddress.streetDetails}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.serviceAddress.city}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.contractPackageName}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.contractSignDate}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.contractEndDate}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.comment}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	
	
</body>
</html>
	
</jsp:root>