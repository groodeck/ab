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
	 		OnSubmitForm = function (){
	 			document.correctionForm.action = document.pressed;
	 			return true;
	 		}	
	 		
	 		calculateRow = function(rowNumber){
	 			var invoiceQuantity = $(("#invoiceService_" + rowNumber + "_quantity")).val();
	 			var correctionQuantity = $(("#correctionService_" + rowNumber + "_quantity")).val();
	 			if(invoiceQuantity && correctionQuantity){
	 				var quantityDiff = correctionQuantity - invoiceQuantity;
	 				$(("#correctionService_" + rowNumber + "_quantityDiff")).val(quantityDiff);
	 			}
	 			var correctionNetPrice = $(("#correctionService_" + rowNumber + "_netPrice")).val();
	 			var netAmount = 0;
	 			if(correctionQuantity && correctionNetPrice){
	 				netAmount = (correctionQuantity * correctionNetPrice).toFixed(2);
	 				$(("#correctionService_" + rowNumber + "_netAmount")).val(netAmount);
	 				
	 				var invoiceNetPrice = $(("#invoiceService_" + rowNumber + "_netPrice")).val();
	 				var netPriceDiff = (correctionNetPrice - invoiceNetPrice).toFixed(2);
	 				$(("#correctionService_" + rowNumber + "_netPriceDiff")).val(netPriceDiff);
	 				
	 				var invoiceNetAmount = $(("#invoiceService_" + rowNumber + "_netAmount")).val();
	 				var netAmountDiff = (netAmount - invoiceNetAmount).toFixed(2);
	 				$(("#correctionService_" + rowNumber + "_netAmountDiff")).val(netAmountDiff);
	 			}
	 			var correctionVatRate = $(("#correctionService_" + rowNumber + "_vatRate")).val();
	 			if(correctionVatRate){
	 				$(("#correctionService_" + rowNumber + "_vatRateDiff")).val(correctionVatRate);

	 				var vatAmount = ((correctionVatRate/100) * netAmount).toFixed(2);
	 				$(("#correctionService_" + rowNumber + "_vatAmount")).val(vatAmount);

	 				var invoiceVatAmount = $(("#invoiceService_" + rowNumber + "_vatAmount")).val();
	 				var vatAmountDiff = (vatAmount - invoiceVatAmount).toFixed(2);
	 				$(("#correctionService_" + rowNumber + "_vatAmountDiff")).val(vatAmountDiff);
	 				
	 				var grossAmount = (new Number(vatAmount) + new Number(netAmount)).toFixed(2);
	 				$(("#correctionService_" + rowNumber + "_grossAmount")).val(grossAmount);
	 				
	 				var invoiceGrossAmount = $(("#invoiceService_" + rowNumber + "_grossAmount")).val();
	 				var grossAmountDiff = (grossAmount - invoiceGrossAmount).toFixed(2);
	 				$(("#correctionService_" + rowNumber + "_grossAmountDiff")).val(grossAmountDiff);
	 			}
	 			calculateSumAmounts();
	 		}	
	 		
	 		calculateSumAmounts = function(){
	 			var rowsCount = $('.deleteButton').length
	 			var sumNetAmount = 0;
	 			var sumVatAmount = 0;
	 			var sumGrossAmount = 0;
	 			for(i = 0; i < rowsCount; i++){
		 			var netAmount = $(("#correctionService_" + i + "_netAmount")).val();
		 			var vatAmount = $(("#correctionService_" + i + "_vatAmount")).val();
		 			var grossAmount = $(("#correctionService_" + i + "_grossAmount")).val();
		 			sumNetAmount = (new Number(sumNetAmount) + new Number(netAmount)).toFixed(2);
		 			sumVatAmount = (new Number(sumVatAmount) + new Number(vatAmount)).toFixed(2);
		 			sumGrossAmount = (new Number(sumGrossAmount) + new Number(grossAmount)).toFixed(2);
	 			}
	 			var invoiceNetAmount = $("#invoiceNetAmount").val();
	 			var invoiceVatAmount = $("#invoiceVatAmount").val();
	 			var invoiceGrossAmount = $("#invoiceGrossAmount").val();
	 			
	 			var netAmountDiff = (new Number(sumNetAmount) - new Number(invoiceNetAmount)).toFixed(2);
	 			var vatAmountDiff = (new Number(sumVatAmount) - new Number(invoiceVatAmount)).toFixed(2);
	 			var grossAmountDiff = (new Number(sumGrossAmount) - new Number(invoiceGrossAmount)).toFixed(2);
	 			
	 			$("#netAmount").val(sumNetAmount);
	 			$("#vatAmount").val(sumVatAmount);
	 			$("#grossAmount").val(sumGrossAmount);
	 			
	 			$("#netAmountDiff").val(netAmountDiff);
	 			$("#vatAmountDiff").val(vatAmountDiff);
	 			$("#grossAmountDiff").val(grossAmountDiff);
	 		}
	 		
	 		changeService = function(rowNum, serviceId){
				//$.getJSON( "/async/getServiceDetails/" + serviceId, function(service) {
				//	var netPriceCtrl = $(("#correctionService_" + rowNum + "_netPrice"));
				//	var vatRateCtrl = $(("#correctionService_" + rowNum + "_vatRate"));
				//	netPriceCtrl.val(service.netPrice);
				//	vatRateCtrl.val(service.vatRate);
				//});
	 		}
	 		
	 	</script>
 	]]>
 </jsp:text>
 
  <body> 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Korekta</h2>
   
   <sf:form method="post" modelAttribute="correction" name="correctionForm" onsubmit="return OnSubmitForm();">
		
		<table style="font-family:sans-serif;" cellspacing="5px" >
			<tr>
				<th align="right"><label>Nr korekty:</label></th>
				<td>
					<sf:input path="correctionNumber" />
				</td>
			</tr>
			<tr>
				<th align="right"><label>Numer korygowanej faktury:</label></th>
				<td>
					<sf:hidden path="invoice.invoiceNumber" />
					<sf:input path="invoice.invoiceNumber" disabled="true"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Okres rozliczeniowy:</label></th>
				<td>
					<sf:hidden path="invoice.settlementPeriodStart" />
					<sf:hidden path="invoice.settlementPeriodEnd" />
					<c:out value="${correction.invoice.settlementPeriodStart} - ${correction.invoice.settlementPeriodEnd}"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Klient:</label></th>
				<td style="border: solid 1px;">
					<sf:hidden path="invoice.subscriber.name" />
					<sf:hidden path="invoice.subscriber.addressStreet" />
					<sf:hidden path="invoice.subscriber.addressCity" />
					<sf:hidden path="invoice.subscriberIdn" />
					<c:out value="${correction.invoice.subscriber.name}" /><br/>
					<c:out value="${correction.invoice.subscriber.addressStreet}" /><br/>
					<c:out value="${correction.invoice.subscriber.addressCity}" />
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data wystawienia korekty:</label></th>
				<td>
					<sf:hidden path="createDate" />
					<custom:date name="createDate" identifier="createDate" value="${correction.createDate}" />
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data wystawienia faktury:</label></th>
				<td>
					<sf:hidden path="invoice.createDate" />
					<custom:date name="invoiceCreateDate" identifier="invoiceCreateDate" value="${correction.invoice.createDate}" 
						editable="false"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data dostarczenia:</label></th>
				<td>
					<sf:hidden path="receiveDate" />
					<custom:date name="receiveDate" identifier="receiveDate" value="${correction.receiveDate}"/>
				</td>
			</tr>
			<tr>
				<th align="right"><label>Data płatności:</label></th>
				<td>
					<sf:hidden path="paymentDate" />
					<custom:date name="paymentDate" identifier="paymentDate" value="${correction.paymentDate}"/>
				</td>
			</tr>
		</table>
		<br/>
		
			<table border="1" cellpadding="2">
				<tr>
					<td>Nazwa usługi</td>
					<td/>
					<td>Ilość</td>
					<td>Cena netto</td>
					<td>Wartość netto</td>
					<td>Stawka VAT</td>
					<td>Wartość VAT</td>
					<td>Wartość brutto</td>
					<td/>
				</tr>
		<c:if test="${not empty correction.serviceRecords}">
				<c:forEach var="service" items="${correction.serviceRecords}" varStatus="status" >
					<tr>
						<td>
							<sf:hidden path="serviceRecords[${status.index}].invoiceRecord.serviceName" />
							<c:out value="${service.invoiceRecord.serviceName}"/>
						</td>
						<td>Przed korektą</td>
						<td>
							<sf:input id="invoiceService_${status.index}_quantity" path="serviceRecords[${status.index}].invoiceRecord.quantity" readonly="true"/>
						</td>
						<td>
							<sf:input id="invoiceService_${status.index}_netPrice" path="serviceRecords[${status.index}].invoiceRecord.netPrice" readonly="true"/>
						</td>
						<td>
							<sf:input id="invoiceService_${status.index}_netAmount" path="serviceRecords[${status.index}].invoiceRecord.netAmount" readonly="true"/>
						</td>
						<td>
							<sf:input id="invoiceService_${status.index}_vatRate" path="serviceRecords[${status.index}].invoiceRecord.vatRate" readonly="true" />
						</td>
						<td>
							<sf:input id="invoiceService_${status.index}_vatAmount" path="serviceRecords[${status.index}].invoiceRecord.vatAmount" readonly="true"/>
						</td>
						<td>
							<sf:input id="invoiceService_${status.index}_grossAmount" path="serviceRecords[${status.index}].invoiceRecord.grossAmount" readonly="true"/>
						</td>
						<td rowspan="2">
							<button class="deleteButton" value="/correction/delRow/${status.index}" onClick="document.pressed=this.value" style="width: 35px;">-</button>
						</td>
					</tr>
					<tr>
						<td rowspan="2"><sf:input path="serviceRecords[${status.index}].serviceName" onchange="changeService(${status.index}, this.value)"/></td>
						<td>Po korekcie</td>
						<td><sf:input id="correctionService_${status.index}_quantity" path="serviceRecords[${status.index}].quantity" onchange="calculateRow(${status.index})" /></td>
						<td><sf:input id="correctionService_${status.index}_netPrice" path="serviceRecords[${status.index}].netPrice" onchange="calculateRow(${status.index})" /></td>
						<td><sf:input id="correctionService_${status.index}_netAmount" path="serviceRecords[${status.index}].netAmount" readonly="true"/></td>
						<td><sf:input id="correctionService_${status.index}_vatRate" path="serviceRecords[${status.index}].vatRate" onchange="calculateRow(${status.index})" /></td>
						<td><sf:input id="correctionService_${status.index}_vatAmount" path="serviceRecords[${status.index}].vatAmount" readonly="true"/></td>
						<td><sf:input id="correctionService_${status.index}_grossAmount" path="serviceRecords[${status.index}].grossAmount" readonly="true"/></td>
					</tr>
					<tr>
						<td>Korekta</td>
						<td><sf:input id="correctionService_${status.index}_quantityDiff" path="serviceRecords[${status.index}].quantityDiff" readonly="true"/></td>
						<td><sf:input id="correctionService_${status.index}_netPriceDiff" path="serviceRecords[${status.index}].netPriceDiff" readonly="true"/></td>
						<td><sf:input id="correctionService_${status.index}_netAmountDiff" path="serviceRecords[${status.index}].netAmountDiff" readonly="true"/></td>
						<td><sf:input id="correctionService_${status.index}_vatRateDiff" path="serviceRecords[${status.index}].vatRate" readonly="true"/></td>
						<td><sf:input id="correctionService_${status.index}_vatAmountDiff" path="serviceRecords[${status.index}].vatAmountDiff" readonly="true"/></td>
						<td><sf:input id="correctionService_${status.index}_grossAmountDiff" path="serviceRecords[${status.index}].grossAmountDiff" readonly="true"/></td>
						<td>
							<c:if test="${status.last}">
								<button value="/correction/addRow" onClick="document.pressed=this.value" style="width: 35px;">+</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
		</c:if>
				<tr>
					<td colspan="3"/>
					<td>Przed korektą</td>
					<td>
						<sf:input id="invoiceNetAmount" path="invoice.netAmount" readonly="true"/>
					</td>
					<td/>
					<td>
						<sf:input id="invoiceVatAmount" path="invoice.vatAmount" readonly="true"/>
					</td>
					<td>
						<sf:input id="invoiceGrossAmount" path="invoice.grossAmount" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="3"/>
					<td>Po korekcie</td>
					<td>
						<sf:input id="netAmount" path="netAmount" readonly="true"/>
					</td>
					<td/>
					<td>
						<sf:input id="vatAmount" path="vatAmount" readonly="true"/>
					</td>	
					<td>
						<sf:input id="grossAmount" path="grossAmount" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="3"/>
					<td>Korekta</td>
					<td>
						<sf:input id="netAmountDiff" path="netAmountDiff" readonly="true"/>
					</td>
					<td/>
					<td>
						<sf:input id="vatAmountDiff" path="vatAmountDiff" readonly="true"/>
					</td>
					<td>
						<sf:input id="grossAmountDiff" path="grossAmountDiff" readonly="true"/>
					</td>
				</tr>
			</table>
		
		<br/>
		<div>
			<button class="deleteButton" value="/correction/delRow/${status.index}" onClick="document.pressed=this.value" style="width: 35px;">-</button>
			<button value="/correction/save" onClick="document.pressed=this.value" >Zapisz</button>
		</div>
		
		</sf:form>
	
</body>
</html>
	
</jsp:root>
