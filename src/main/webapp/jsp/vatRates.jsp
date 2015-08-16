<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
 
 <jsp:text>
 	<![CDATA[
 		<script type="text/javascript">
	 		saveNewVatRate = function (){
	 			var vatRateDesc = $("#newVatRateDesc").val();
	 			var vatRateValue = $("#newVatRateValue").val();
	 			$.getJSON( "/vatRates/saveNewVatRate/" + vatRateDesc + "/" + vatRateValue, function(rate) {
	 				if( rate!= null){
	 					var tableRowSet = $('#vatRateTable tr');
	 					var rowCount = tableRowSet.length-1;
						tableRowSet.last().after('<tr><td><input type="text" class="form-control" name="rates['+rowCount+'].rateIdn" value="' + rate.rateIdn + '" size="15" disabled="true" />'
							+'</td><td><input type="text" class="form-control" name="rates['+rowCount+'].rateDesc" value="' + rate.rateDesc + '" size="15" disabled="true" />'
							+'</td><td><input type="text" class="form-control" name="rates['+rowCount+'].value" value="' + rate.value + '" size="15" disabled="true" />'
							+'</td></tr>');
					}
				});
	 			
	 		}	
	 	</script>
 	]]>
 </jsp:text>
 
   <custom:message uiMessage="${uiMessage}"/>
   
    <h3>Stawki VAT</h3>
		
		<div class="row">
			<div class="col-md-6">
	            <div class="form-group row">
	                <div class="col-md-4">
	                    <input  id="newVatRateDesc" class="form-control" placeholder="Nazwa - identyfikator"/>
	                </div>
	                <div class="col-md-4">
	                    <input  id="newVatRateValue" class="form-control" placeholder="Wysokość stawki"/>
	                </div>
	                <div class="col-md-4">
	                    <button class="btn btn-default" onclick="saveNewVatRate()">Dodaj</button>
	                </div>
	            </div>
	        </div>
		</div>
		
		
		<sf:form method="post" action="/vatRates/save" modelAttribute="vatRates" >
		<fieldset>
			<table class="table col-lg-6" id="vatRateTable" >
				<thead>
					<tr>
						<th>Identyfikator</th>
						<th>Nazwa stawki</th>
						<th>Wysokość stawki</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="rate" items="${vatRates.rates}" varStatus="status" >
						<tr>
							<td>
								<sf:input class="form-control" path="rates[${status.index}].rateIdn" size="15" disabled="true" />
								<sf:hidden path="rates[${status.index}].rateIdn"/> 
							</td>
							<td>
								<sf:input class="form-control" path="rates[${status.index}].rateDesc" size="15" disabled="true" />
								<sf:hidden path="rates[${status.index}].rateDesc"/> 
							</td>
							<td>
								<sf:input class="form-control" path="rates[${status.index}].value" size="15" disabled="true" />
								<sf:hidden path="rates[${status.index}].value"/> 
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</fieldset>
		</sf:form>
		
</jsp:root>