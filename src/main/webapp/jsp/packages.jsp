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
	 		editContractPackage = function (packageId){
	 			window.location.href = '/package/edit/'+packageId;
	 		}	
	 	</script>
 	]]>
 </jsp:text>
 
 <body> 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Pakiety</h2>
		
		<a href="/package/new"><button>Dodaj pakiet</button></a>
		<a href="/packages/showAll"><button>Pokaż wszystkie</button></a>
   
		<c:if test="${not empty contractPackages}">
			<br/><br/>
			<table border="1" cellspacing="0" cellpadding="2" class="borderedTable">
				<tr class="tableHeader">
					<td>lp</td>
					<td>Typ klienta</td>
					<td>Nazwa pakietu</td>
					<td>Status</td>
					<td>Abonament</td>
					<td>Aktywacja netto</td>
					<td>Aktywacja brutto</td>
					<td>Instalacyjna netto</td>
					<td>Instalacyjna brutto</td>
				</tr>
				<c:forEach var="contractPackage" items="${contractPackages}" varStatus="status" >
					<tr>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.clientTypeDesc}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.packageName}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.packageActive ? 'Aktywny' : 'Nieaktywny'}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.packageSubscription}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.activationFeeNet}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.activationFeeGross}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.installationFeeNet}"/></td>
						<td onclick="editContractPackage(${contractPackage.packageId})"><c:out value="${contractPackage.installationFeeGross}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	
</body>
</html>
	
</jsp:root>