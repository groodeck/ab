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

   <h2>Wpłata</h2>
   <sf:form method="post" action="/payment/save" modelAttribute="payment" >
		<fieldset>
		
		<div id="paymentDetailsDiv" align="center" style="float:left; width: 70%;">
		
		<sf:hidden path="paymentId"/>
		<table width="100%">
			<tr>
				<th align="right" style="width: 25%"><label for="subscriber_number">Numer abonenta:</label></th>
				<td colspan="4">
					<c:out value="${payment.subscriber.subscriberIdn}" />
				</td>
			</tr>
			<tr>
				<th align="right" style="width: 25%"><label for="subscriber_name">Abonent:</label></th>
				<td colspan="4">
					<c:out value="${payment.subscriber.effectiveName}"/>
				</td>
			</tr>
			<tr>
				<th align="right" style="width: 25%"><label for="create_date">Data wpłaty:</label></th>
				<td colspan="4">
					<custom:date name="createDate" identifier="createDate" 
						value="${payment.createDate}" additionalAttributes="size='15'"/>
				</td>
			</tr>
			<tr>
				<th align="right" style="width: 25%"><label for="payment_amount">Kwota wpłaty:</label></th>
				<td colspan="4">
					<sf:input path="paymentAmount" size="8" id="payment_amount" />
				</td>
			</tr>
			<c:if test="${not empty payment.invoices}">
				<tr>
					<th align="right"><label for="invoices">Rozlicz faktury:</label></th>
					<td>
						<table cellpadding="5">
							<tr>
								<td/>
								<td>Faktura</td>
								<td>Wartość faktury</td>
								<td>Do spłaty</td>
								<td>Kwota spłaty</td>
							</tr>
						<c:forEach var="invoice" items="${payment.invoices}" varStatus="status">
							<tr>	
								<td><sf:checkbox path="invoices[${status.index}].shouldBePaid"/></td>
								<td nowrap="nowrap">
									<sf:hidden path="invoices[${status.index}].invoiceId"/>
									<sf:hidden path="invoices[${status.index}].invoiceNumber"/>
									<sf:hidden path="invoices[${status.index}].settlementPeriod"/>
									<c:out value="${invoice.invoiceNumber}(${invoice.settlementPeriod})"/>
								</td><td>
									<sf:hidden path="invoices[${status.index}].invoiceGrossAmount"/>
									<c:out value="${invoice.invoiceGrossAmount}"/>
								</td><td>
									<sf:hidden path="invoices[${status.index}].invoiceLeftToPay"/>
									<c:out value="${invoice.invoiceLeftToPay}"/>
								</td>
								<td><sf:input path="invoices[${status.index}].paymentAmount" size="8"/></td>
							</tr>
						</c:forEach>
						</table>
					</td>
				</tr>
			</c:if>
			<c:if test="${empty payment.invoices}">
				<tr>
					<td/>
					<td colspan="4">Brak faktury do rozliczenia</td>
				</tr>				
			</c:if>
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