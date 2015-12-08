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
   
		<c:if test="${not empty contractPackages.records}">
			<br/><br/>
			<table class="table">
				<thead>
					<tr class="tableHeader">
						<td>lp</td>
						<custom:sortableHeader column="${tableHeader.columns.clientTypeDesc}" sortUrl="/packages/sort/" />
						<custom:sortableHeader column="${tableHeader.columns.packageName}" sortUrl="/packages/sort/" />
						<custom:sortableHeader column="${tableHeader.columns.packageActive}" sortUrl="/packages/sort/" />
						<custom:sortableHeader column="${tableHeader.columns.packageSubscription}" sortUrl="/packages/sort/" />
						<custom:sortableHeader column="${tableHeader.columns.activationFeeNet}" sortUrl="/packages/sort/" />
						<custom:sortableHeader column="${tableHeader.columns.activationFeeGross}" sortUrl="/packages/sort/" />
						<custom:sortableHeader column="${tableHeader.columns.installationFeeNet}" sortUrl="/packages/sort/" />
						<custom:sortableHeader column="${tableHeader.columns.installationFeeGross}" sortUrl="/packages/sort/" />
					</tr>
				</thead>
				<c:forEach var="contractPackage" items="${contractPackages.records}" varStatus="status" >
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
			
			<custom:pageNav page="${contractPackages}" pageChangeUrl="/packages/changePage/"/>
		</c:if>
	
</body>
</html>
	
</jsp:root>