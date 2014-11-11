<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
	
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
     
 <html xmlns="http://www.w3.org/1999/xhtml">
 
 <jsp:text>
 	<![CDATA[
 		<script type="text/javascript" src="data/js/common.js" ></script>
 		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
	 	</script>
 	]]>
 </jsp:text>
	 
 <body> 
   <h2>Abonent</h2>
   <sf:form method="post" action="/subscriber/save" modelAttribute="subscriber" >
		<fieldset>
		<div align="center" style="float: left; width: 50%">
		<h3>Dane abonenta</h3>
		<table>
			<tr>
				<th><label for="client_type">Typ klienta:</label></th>
				<td>
					<sf:select path="clientType" items="${clientTypes}" id="client_type"/>
				</td>
			</tr>
			<tr id="individualNameRow">
				<th><label for="client_name">Imię:</label></th>
				<td><sf:input path="name" size="15" id="client_name" /></td>
			</tr>
			<tr id="individualSurnameRow">
				<th><label for="client_surname">Nazwisko/Nazwa firmy:</label></th>
				<td><sf:input path="surname" size="15" id="client_surname" /></td>
			</tr>
			<tr id="companyNameRow">
				<th><label for="company_name">Nazwa firmy:</label></th>
				<td><sf:input path="companyName" size="15" id="company_name" /></td>
			</tr>
			<tr>
				<th><label for="client_id_number">Numer dowodu:</label></th>
				<td><sf:input path="clientIdNumber" size="15" id="client_id_number" /></td>
			</tr>
			<tr>
				<th><label for="phone_number">Numer telefonu:</label></th>
				<td>
					<table id="phoneTable">
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
			<tr>
				<th><label for="pesel">PESEL:</label></th>
				<td><sf:input path="pesel" size="15" id="pesel" /></td>
			</tr>
			<tr>
				<th><label for="regon">REGON:</label></th>
				<td><sf:input path="regon" size="15" id="regon" /></td>
			</tr>
			<tr>
				<th><label for="nip">NIP:</label></th>
				<td><sf:input path="nip" size="15" id="nip" /></td>
			</tr>
			<tr>
				<th><label for="email">Email:</label></th>
				<td>
					<table id="emailTable">
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
		<table>
			<tr>
				<th><label for="city">Miasto:</label></th>
				<td><sf:input path="mainAddress.city" size="15" id="city" /></td>
			</tr>
			<tr>
				<th><label for="zip_code">Kod pocztowy:</label></th>
				<td><sf:input path="mainAddress.zipCode" size="15" id="zip_code" /></td>
			</tr>
			<tr>
				<th><label for="street">Ulica:</label></th>
				<td><sf:input path="mainAddress.street" size="15" id="street" /></td>
			</tr>
			<tr>
				<th><label for="house_no">Numer domu:</label></th>
				<td><sf:input path="mainAddress.houseNo" size="15" id="house_no" /></td>
			</tr>
			<tr>
				<th><label for="apartament_no">Numer mieszkania:</label></th>
				<td><sf:input path="mainAddress.apartamentNo" size="15" id="apartament_no"/></td>
			</tr>
		</table>
		
		</div>
		<div align="center" style="float: left; width: 50%">
		<h3>Umowa</h3>
		<table style="font-family:sans-serif;" >
			<tr>
				<th><label for="subscriber_id">Nr abonenta:</label></th>
				<td><sf:input path="subscriberId" size="15" id="subscriber_id" /></td>
			</tr>
			<tr>
				<th><label for="contract_id">Nr umowy:</label></th>
				<td><sf:input path="currentContract.contractId" size="15" id="contract_id" /></td>
			</tr>
			<tr>
				<th><label for="contract_status">Status:</label></th>
				<td><sf:input path="currentContract.contractStatus" size="15" id="contract_status" /></td>
			</tr>
			<tr>
				<th><label for="contract_sign_date">Data podpisania umowy:</label></th>
				<td>
					
					<custom:date name="currentContract.contractSignDate" identifier="currentContractSignDate" 
						value="${subscriber.currentContract.contractSignDate}" />
				</td>
			</tr>
			<tr>
				<th><label for="contract_activation_date">Data aktywacji:</label></th>
				<td><sf:input path="currentContract.contractActivationDate" size="15" id="contract_activation_date" /></td>
			</tr>
			<tr>
				<th><label for="contract_period">Okres obowiązywania umowy:</label></th>
				<td><sf:input path="currentContract.contractPeriod" size="15" id="contract_period" /></td>
			</tr>
			<tr>
				<th><label for="contract_pack">Pakiet:</label></th>
				<td><sf:input path="currentContract.contractPack" size="15" id="contract_pack" /></td>
			</tr>
			<tr>
				<th><label for="contract_subscription">Abonament miesięczny:</label></th>
				<td><sf:input path="currentContract.contractSubscription" size="15" id="contract_subscription" /></td>
			</tr>
			<tr>
				<th><label for="installation_fee">Opłata za wykonanie instalacji:</label></th>
				<td><sf:input path="currentContract.installationFee" size="15" id="installation_fee" /></td>
			</tr>
			<tr>
				<th><label for="balance">Saldo:</label></th>
				<td><sf:input path="balance" size="15" id="balance" /></td>
			</tr>
		</table>
		</div>
		<input type="submit" value="Zapisz"/>
		</fieldset>
	</sf:form>
	
</body>
</html>
	
</jsp:root>