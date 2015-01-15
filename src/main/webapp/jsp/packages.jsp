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
   
   <h2>Pakiety</h2>
		
		<a href="/package/new"><button>Dodaj pakiet</button></a>
   
		<c:if test="${not empty contractPackages}">
			<br/><br/>
			<table border="1" cellspacing="0" cellpadding="2">
				<tr>
					<td>lp</td>
					<td>Nazwa pakietu</td>
					<td>Abonent</td>
					<td>Opłata instalacyjna</td>
					<td>Opłata aktywacyjna</td>
				</tr>
				<c:forEach var="package" items="${contractPackages}" varStatus="status" >
					<tr>
						<td onclick="editContractPackage(${package.packageId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="editContractPackage(${package.packageId})"><c:out value="${package.packageName}"/></td>
						<td onclick="editContractPackage(${package.packageId})"><c:out value="${package.packageSubscription}"/></td>
						<td onclick="editContractPackage(${package.packageId})"><c:out value="${package.installationFeeGross}"/></td>
						<td onclick="editContractPackage(${package.packageId})"><c:out value="${package.activationFeeGross}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	
</body>
</html>
	
</jsp:root>