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
	 		function addRow(tableName, inputName){
	 			var tableRowSet = $('#'+ tableName +' tr');
	 			var rowCount = tableRowSet.length;
				tableRowSet.last().after('<tr><td><input type="text" name="'+ inputName +'['+rowCount+']" value="" size="15"/></td></tr>');
		 	}
			 
	 		function delRow(tableName){
				if($('#'+ tableName +' tr').size()>1){
					$('#'+ tableName +' tr:last-child').remove();
				}
	 		}
	 		
	 		addDeviceRow = function () {
	 			var tableRowSet = $('#deviceTable tr');
	 			var rowCount = tableRowSet.length-1;
				tableRowSet.last().after(
					 '<tr><td><select name="currentContract.devices['+rowCount+'].deviceType" id="devices_'+rowCount+'_deviceType" ></select></td>'
				   + '<td><input name="currentContract.devices['+rowCount+'].serialNumber" size="15"/></td>'
				   + '<td><input name="currentContract.devices['+rowCount+'].mac" size="15"/></td>'
				   + '<td><input name="currentContract.devices['+rowCount+'].ip" size="15"/></td></tr>');
				$("#devices_0_deviceType").find('option').clone().appendTo("#devices_"+rowCount+"_deviceType");
		 	}
		 	
		 	delDeviceRow = function () {
				if($('#deviceTable tr').size()>2){
					$('#deviceTable tr:last-child').remove();
				}
	 		}
	 		
	 		function refreshClientType(){
	 			var select = $("#clientType");
	 			if(select.val() == 'INDIVIDUAL'){
	 				$(".individualRow").show();
	 				$(".companyRow").hide();
	 			} else {
	 				$(".individualRow").hide();
	 				$(".companyRow").show();
	 			}
	 			$.getJSON( "/async/getPackages/" + select.val(), function(packages) {
	 				var packageSelect = $("#contract_pack"); 
	 				var packageSelected = packageSelect.val();                      
				    packageSelect.find('option').remove();                          
				    $.each(packages, function(key, value) {              
				        $('<option>').val(key).text(value).appendTo(packageSelect);     
				    });
				   	packageSelect.val(packageSelected);
					refreshContractPack();
				});
	 		}
	 		
	 		function changeAddressVisible(checkbox, tableId){
	 			if($(checkbox).attr('checked')){
	 				$('#'+ tableId).show();
	 			} else {
	 				$('#'+ tableId).hide();
	 			}
	 		}
	 		
	 		refreshContractEndDate = function (){
	 			var period = $("#contract_period").val();
	 			var startDateCntrl = $("#datepickercurrentContractActivationDate");
		 		var endDateCntrl = $("#datepickercurrentContractEndDate");
	 			if(isNaN(period)){
	 				endDateCntrl.val('');
	 			} else {
	 				var startDate = new Date(startDateCntrl.val());
		 			startDate.setMonth(startDate.getMonth() + parseInt(period));
		 			endDateCntrl.val($.datepicker.formatDate('yy-mm-dd', startDate));
	 			}
	 		}
	 		
	 		refreshContractPack = function (){
	 			var packageId = $("#contract_pack").val();
	 			$.getJSON( "/async/getPackageDetails/" + packageId, function(data) {
	 				$("#activationFeeNet").val(data.activationFeeNet)
	 				$("#activationFeeVatRate").val(data.activationFeeVatRate)
	 				$("#activationFeeVat").val(data.activationFeeVat)
	 				$("#activationFeeGross").val(data.activationFeeGross)
	 				$("#installationFeeNet").val(data.installationFeeNet)
	 				$("#installationFeeVatRate").val(data.installationFeeVatRate)
	 				$("#installationFeeVat").val(data.installationFeeVat)
	 				$("#installationFeeGross").val(data.installationFeeGross)
	 				$("#contract_subscription").val(data.subscription)
				})
	 		}	
	 		
	 		$(document).ready(function() {
			    refreshClientType();
			    changeAddressVisible($( "#correspAddressCheckbox" ), 'correspondenceAddressTable');
			    changeAddressVisible($( "#serviceAddressSet" ), 'serviceAddressTable');
			    refreshContractEndDate();
			});
			
	 	</script>
 	]]>
 </jsp:text>
	 
 <body> 
  
  	<custom:message uiMessage="${uiMessage}"/>
  
   <h2>Abonent</h2>
   <sf:form method="post" action="/subscriber/save" modelAttribute="subscriber" >
		<fieldset>
		
		<div id="subscriberDataDiv" align="center" style="float:left; width: 50%;">
		<h3>Dane abonenta</h3>
		
		<sf:hidden path="subscriberId"/>
		
		<table width="100%">
			<tr>
				<th align="right" style="width: 40%"><label for="subscriber_idn">Nr abonenta:</label></th>
				<td>
					<sf:input path="subscriberIdn" size="15" id="subscriber_idn" disabled="true" />
					<sf:hidden path="subscriberIdn"/> 
				</td>
			</tr>
			<tr>
				<th align="right"><label for="client_type">Typ klienta:</label></th>
				<td>
					<sf:select path="clientType" items="${clientTypes}" id="clientType" onchange="refreshClientType()"/>
				</td>
			</tr>
			<tr class="individualRow">
				<th align="right"><label for="client_name">Imię:</label></th>
				<td><sf:input path="name" size="15" id="client_name" /></td>
			</tr>
			<tr class="individualRow">
				<th align="right"><label for="client_surname">Nazwisko:</label></th>
				<td><sf:input path="surname" size="15" id="client_surname" /></td>
			</tr>
			<tr class="companyRow">
				<th align="right"><label for="company_name">Nazwa firmy:</label></th>
				<td><sf:input path="companyName" size="15" id="company_name" /></td>
			</tr>
			<tr>
				<th align="right"><label for="phone_number">Numer telefonu:</label></th>
				<td>
					<table id="phoneTable" cellpadding="0" cellspacing="0">
						<c:forEach var="phoneNumber" items="${subscriber.phoneNumbers}" varStatus="status" >
							<tr>
								<td>
									<input name="phoneNumbers[${status.index}]" value="${phoneNumber}" size="15" id="phone_number" />
								</td>
								<c:if test="${status.first}">
									<td valign="top">
										<button type="button" onclick="addRow('phoneTable', 'phoneNumbers')">+</button>
										<button type="button" onclick="delRow('phoneTable')">-</button>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr class="individualRow">
				<th align="right"><label for="client_id_number">Numer dowodu:</label></th>
				<td><sf:input path="clientIdNumber" size="15" id="client_id_number" /></td>
			</tr>
			<tr class="individualRow">
				<th align="right"><label for="pesel">PESEL:</label></th>
				<td><sf:input path="pesel" size="15" id="pesel" /></td>
			</tr>
			<tr class="companyRow">
				<th align="right"><label for="regon">REGON:</label></th>
				<td><sf:input path="regon" size="15" id="regon" /></td>
			</tr>
			<tr>
				<th align="right"><label for="nip">NIP:</label></th>
				<td><sf:input path="nip" size="15" id="nip" /></td>
			</tr>
			<tr>
				<th align="right"><label for="email">Email:</label></th>
				<td>
					<table id="emailTable" cellpadding="0" cellspacing="0">
						<c:forEach var="email" items="${subscriber.emails}" varStatus="status">
							<tr>
								<td>
									<input name="emails[${status.index}]" value="${email}" size="25" id="email" />
								</td>
								<c:if test="${status.first}">
									<td valign="top">
										<button type="button" onclick="addRow('emailTable', 'emails')">+</button>
										<button type="button" onclick="delRow('emailTable')">-</button>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
		
		<h3>Adres</h3>
			<table width="100%">
				<tr>
					<th align="right" style="width: 40%"><label for="city">Miasto:</label></th>
					<td>
						<sf:select path="mainAddress.city" items="${cities}" id="city"/>
					</td>
				</tr>
				<tr>
					<th align="right"><label for="zip_code">Kod pocztowy:</label></th>
					<td><sf:input path="mainAddress.zipCode" size="15" id="zip_code" /></td>
				</tr>
				<tr>
					<th align="right"><label for="street">Ulica:</label></th>
					<td><sf:input path="mainAddress.street" size="15" id="street" /></td>
				</tr>
				<tr>
					<th align="right"><label for="house_no">Numer domu:</label></th>
					<td><sf:input path="mainAddress.houseNo" size="15" id="house_no" /></td>
				</tr>
				<tr>
					<th align="right"><label for="apartament_no">Numer mieszkania:</label></th>
					<td><sf:input path="mainAddress.apartamentNo" size="15" id="apartament_no"/></td>
				</tr>
			</table>
			
			<h3>Adres świadczenia usługi <sf:checkbox id="serviceAddressSet" path="serviceAddressSet" 
				onclick="changeAddressVisible(this, 'serviceAddressTable')"/> </h3>
			<table id="serviceAddressTable"  width="100%">
				<tr>
					<th align="right" style="width: 40%"><label for="city">Miasto:</label></th>
					<td>
						<sf:select path="serviceAddress.city" items="${cities}" id="city"/>
					</td>
				</tr>
				<tr>
					<th align="right"><label for="zip_code">Kod pocztowy:</label></th>
					<td><sf:input path="serviceAddress.zipCode" size="15" id="zip_code" /></td>
				</tr>
				<tr>
					<th align="right"><label for="street">Ulica:</label></th>
					<td><sf:input path="serviceAddress.street" size="15" id="street" /></td>
				</tr>
				<tr>
					<th align="right"><label for="house_no">Numer domu:</label></th>
					<td><sf:input path="serviceAddress.houseNo" size="15" id="house_no" /></td>
				</tr>
				<tr>
					<th align="right"><label for="apartament_no">Numer mieszkania:</label></th>
					<td><sf:input path="serviceAddress.apartamentNo" size="15" id="apartament_no"/></td>
				</tr>
			</table>
			
			<h3>Adres wysyłki faktury <sf:checkbox id="correspAddressCheckbox" path="correspondenceAddressSet" 
				onclick="changeAddressVisible(this, 'correspondenceAddressTable')"/> </h3>
			<table id="correspondenceAddressTable">
				<tr>
					<th align="right" style="width: 40%"><label for="city">Miasto:</label></th>
					<td>
						<sf:select path="correspondenceAddress.city" items="${cities}" id="city"/>
					</td>
				</tr>
				<tr>
					<th align="right"><label for="zip_code">Kod pocztowy:</label></th>
					<td><sf:input path="correspondenceAddress.zipCode" size="15" id="zip_code" /></td>
				</tr>
				<tr>
					<th align="right"><label for="street">Ulica:</label></th>
					<td><sf:input path="correspondenceAddress.street" size="15" id="street" /></td>
				</tr>
				<tr>
					<th align="right"><label for="house_no">Numer domu:</label></th>
					<td><sf:input path="correspondenceAddress.houseNo" size="15" id="house_no" /></td>
				</tr>
				<tr>
					<th align="right"><label for="apartament_no">Numer mieszkania:</label></th>
					<td><sf:input path="correspondenceAddress.apartamentNo" size="15" id="apartament_no"/></td>
				</tr>
			</table>
		</div>
		
		
		<div id="contractDiv" align="center" style="float:left; width: 50%">
		<h3>Umowa</h3>
		<table style="font-family:sans-serif;"  width="100%" >
			<tr>
				<th align="right" style="width: 40%"><label for="contract_idn">Nr umowy:</label></th>
				<td>
					<sf:input path="currentContract.contractIdn" size="15" id="contract_idn" disabled="true" />
					<sf:hidden path="currentContract.contractIdn"/> 
				</td>
			</tr>
			<tr>
				<th align="right"><label for="contract_status">Status:</label></th>
				<td>
					<sf:select path="currentContract.contractStatus" items="${contractStatuses}" id="contract_status"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label for="contract_sign_date">Data podpisania umowy:</label></th>
				<td>
					<custom:date name="currentContract.contractSignDate" identifier="currentContractSignDate" 
						value="${subscriber.currentContract.contractSignDate}" additionalAttributes="size='15'"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label for="contract_activation_date">Data aktywacji:</label></th>
				<td>
					<custom:date name="currentContract.contractActivationDate" identifier="currentContractActivationDate" 
						value="${subscriber.currentContract.contractActivationDate}" additionalAttributes="size='15' onChange='refreshContractEndDate()'" />
				</td>
			</tr>
			<tr>
				<th align="right"><label for="contract_period">Okres obowiązywania umowy:</label></th>
				<td><sf:select path="currentContract.contractPeriod" items="${contractDurations}" id="contract_period"
					onchange="refreshContractEndDate()"/></td>
			</tr>
			<tr>
				<th align="right"><label for="contract_end_date">Data zakończenia umowy:</label></th>
				<td>
					<custom:date name="currentContract.contractEndDate" identifier="currentContractEndDate" 
						value="${subscriber.currentContract.contractEndDate}" additionalAttributes="size='15'"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label for="contract_pack">Pakiet:</label></th>
				<td>
					<sf:select path="currentContract.contractPack" items="${packages}" id="contract_pack"
						onchange="refreshContractPack()"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label for="contract_subscription">Abonament miesięczny:</label></th>
				<td><sf:input path="currentContract.contractSubscription" size="15" id="contract_subscription" /></td>
			</tr>
			<tr>
				<th align="right"><label for="activationFeeNet">Opłata aktywacyjna:</label></th>
				<td>Netto:<sf:input path="currentContract.activationFeeNet" size="3" id="activationFeeNet" />
				VAT:<sf:hidden path="currentContract.activationFeeVatRate" id="activationFeeVatRate" />
					<sf:input path="currentContract.activationFeeVat" size="3" id="activationFeeVat" />
				Brutto:<sf:input path="currentContract.activationFeeGross" size="3" id="activationFeeGross" /></td>
			</tr>
			<tr>
				<th align="right"><label for="installationFeeNet">Opłata instalacyjna:</label></th>
				<td>Netto:<sf:input path="currentContract.installationFeeNet" size="3" id="installationFeeNet" />
				VAT:<sf:hidden path="currentContract.installationFeeVatRate" id="installationFeeVatRate" />
					<sf:input path="currentContract.installationFeeVat" size="3" id="installationFeeVat" />
				Brutto:<sf:input path="currentContract.installationFeeGross" size="3" id="installationFeeGross" /></td>
			</tr>
			<tr>
				<th align="right"><label for="balance">Saldo:</label></th>
				<td><sf:input path="balance" size="15" id="balance" /></td>
			</tr>
			<tr>
				<th align="right"><label for="balance">Uwagi:</label></th>
				<td><sf:textarea rows="3" path="comment" cols="40" id="comment" /></td>
			</tr>
			<tr>
				<th align="right"><label for="balance">Dodatkowe uwagi:</label></th>
				<td><sf:textarea rows="3" path="additionalComment" cols="40" id="additionalComment" /></td>
			</tr>
		</table>
		
		<h3>Urządzenia</h3>
		<table style="font-family:sans-serif;" width="100%" id="deviceTable" >
			<tr>
				<td>Typ urządzenia</td>
				<td>Nr Seryjny</td>
				<td>Nr MAC</td>
				<td>IP adres</td>
			</tr>
			<c:forEach var="device" items="${subscriber.currentContract.devices}" varStatus="status">
			<tr>
				<td>
					<sf:select path="currentContract.devices[${status.index}].deviceType" items="${deviceTypes}" id="devices_${status.index}_deviceType" /></td>
				<td>
					<sf:input path="currentContract.devices[${status.index}].serialNumber" size="15"/></td>
				<td>
					<sf:input path="currentContract.devices[${status.index}].mac" size="15"/></td>
				<td>
					<sf:input path="currentContract.devices[${status.index}].ip" size="15"/></td>
					
				<c:if test="${status.first}">
					<td valign="top">
						<button type="button" onclick="addDeviceRow()">+</button>
						<button type="button" onclick="delDeviceRow()">-</button>
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