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
	 		
	 		addServiceRow = function () {
	 			var tableRowSet = $('#serviceTable tr');
	 			var rowCount = tableRowSet.length-1;
				tableRowSet.last().after(
					 '<tr>'
				   + '<td><input name="services['+rowCount+'].serviceName" size="20"/></td>'
				   + '<td><input name="services['+rowCount+'].subscriptionNet" size="15"/></td>'
				   + '<td><select name="services['+rowCount+'].vatRate" id="services_'+rowCount+'_vatRate" ></select></td>'
				   + '<td><input name="services['+rowCount+'].vatAmount" size="15"/></td>'
				   + '<td><input name="services['+rowCount+'].subscriptionGross" size="15"/></td>'
				   + '</tr>');
				$("#services_0_vatRate").find('option').clone().appendTo("#services_"+rowCount+"_vatRate");
		 	}
		 	
		 	delServiceRow = function () {
				if($('#serviceTable tr').size()>2){
					$('#serviceTable tr:last-child').remove();
				}
	 		}
	 		
	 		refreshContractPack = function (){
	 			var packageId = $("#contract_pack").val();
	 			$.getJSON( "/async/getPackageDetails/" + packageId, function(data) {
	 				$("#activation_fee").val(data.activationFee)
	 				$("#installation_fee").val(data.installationFee)
	 				$("#contract_subscription").val(data.subscription)
				})
	 		}	
	 		
	 		$(document).ready(function() {

			});
	 	</script>
 	]]>
 </jsp:text>
	 
 <body> 
  
  	<custom:message uiMessage="${uiMessage}"/>
  
   <h2>Pakiet</h2>
   <sf:form method="post" action="/package/save" modelAttribute="contractPackage" >
		<fieldset>
		
		<div id="packageDetailsDiv" align="center" style="float:left; width: 70%;">
		
		<sf:hidden path="packageId"/>
		<table width="100%">
			<tr>
				<th align="right" style="width: 30%"><label for="package_name">Typ klienta:</label></th>
				<td colspan="4">
					<sf:select path="clientType" items="${clientTypes}" id="client_type" />
				</td>
			</tr>
			<tr>
				<th align="right" style="width: 30%"><label for="package_name">Nazwa pakietu:</label></th>
				<td colspan="4">
					<sf:input path="packageName" size="30" id="package_name" />
				</td>
			</tr>
			<tr>
				<th align="right"><label for="subscription">Abonament:</label></th>
				<td colspan="4"><sf:input path="packageSubscription" size="15" id="subscription" /></td>
			</tr>
			<tr>
				<td/>
				<th>Netto</th>
				<th>Stawka VAT</th>
				<th>VAT</th>
				<th>Brutto</th>
			</tr>
			<tr>
				<th align="right"><label for="activation_fee">Opłata aktywacyjna:</label></th>
				<td><sf:input path="activationFeeNet" size="15" id="activation_fee_net" /></td>
				<td><sf:select path="activationFeeVatRate" items="${vatRates}" id="activation_fee_vat_rate"/></td>
				<td><sf:input path="activationFeeVat" size="15" id="activation_fee_vat" /></td>
				<td><sf:input path="activationFeeGross" size="15" id="activation_fee_gross" /></td>
			</tr>
			<tr>
				<th align="right"><label for="installation_fee">Opłata instalacyjna:</label></th>
				<td><sf:input path="installationFeeNet" size="15" id="installation_fee" /></td>
				<td><sf:select path="installationFeeVatRate" items="${vatRates}" id="installation_fee_vat_rate"/></td>
				<td><sf:input path="installationFeeVat" size="15" id="installation_fee_vat" /></td>
				<td><sf:input path="installationFeeGross" size="15" id="installation_fee_gross" /></td>
			</tr>
		</table>
		
		<h3 align="left">Usługi:</h3>
		<table style="font-family:sans-serif;" width="100%" id="serviceTable" border="1">
			<tr>
				<td>Nazwa usługi</td>
				<td>Cena netto</td>
				<td>Stawka VAT</td>
				<td>VAT</td>
				<td>Cena brutto</td>
			</tr>
			<c:forEach var="service" items="${contractPackage.services}" varStatus="status">
			<tr>
				<td>
					<sf:input path="services[${status.index}].serviceName" size="25"/></td>
				<td>
					<sf:input path="services[${status.index}].subscriptionNet" size="15"/></td>
				<td>
					<sf:select path="services[${status.index}].vatRate" 
						items="${vatRates}" id="services_${status.index}_vatRate"/></td>
				<td>
					<sf:input path="services[${status.index}].vatAmount" size="15"/></td>
				<td>
					<sf:input path="services[${status.index}].subscriptionGross" size="15"/></td>
					
				<c:if test="${status.first}">
					<td nowrap="nowrap">
						<button type="button" onclick="addServiceRow()">+</button>
						<button type="button" onclick="delServiceRow()">-</button>
					</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
		
		</div>

		<div id="submitDiv" align="center" style="clear:both; width: 100%; height:50px; vertical-align: middle;">
			<input type="submit" value="Zapisz"/>
		</div>
		</fieldset>
	</sf:form>
	
</body>
</html>
	
</jsp:root>