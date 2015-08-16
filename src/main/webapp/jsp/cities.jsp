<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
 
 <jsp:text>
 	<![CDATA[
 		<script type="text/javascript">
	 		saveNewCity = function (){
	 			//window.location.href = '/package/edit/'+packageId;
	 			var newCityDesc = $("#newCityDesc").val();
	 			$.getJSON( "/cities/saveNewCity/" + newCityDesc, function(city) {
	 				if( city!= null){
	 					var tableRowSet = $('#cityTable tr');
	 					var rowCount = tableRowSet.length-1;
						tableRowSet.last().after('<tr><td><input type="text" name="cities['+rowCount+'].cityIdn" value="' + city.cityIdn + '" size="15" disabled="true" />'
							+'<input type="hidden" name="cities['+rowCount+'].cityIdn" value="' + city.cityIdn + '" />' 
							+'</td><td><input type="text" name="cities['+rowCount+'].cityDesc" value="' + city.cityDesc + '" size="15" /></td></tr>');
					}
				});
	 			
	 		}	
	 	</script>
 	]]>
 </jsp:text>
 
   <custom:message uiMessage="${uiMessage}"/>
   
    <h2>Miasta</h2>
		
		<div class="row">
		  <div class="col-lg-3">
		    <div class="input-group">
				<input  id="newCityDesc" class="form-control" placeholder="Nowe miasto"/>
				<span class="input-group-btn">
					<button class="btn btn-default" onclick="saveNewCity()">Dodaj</button>
		      	</span>
		    </div>
		  </div>
		</div>
		
		
		<sf:form method="post" action="/cities/save" modelAttribute="cities" >
		<fieldset>
			<table class="table" id="cityTable">
				<thead>
					<tr>
						<th>Identyfikator</th>
						<th>Nazwa miasta</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="city" items="${cities.cities}" varStatus="status" >
						<tr>
							<td>
								<sf:input path="cities[${status.index}].cityIdn" size="15" disabled="true" />
								<sf:hidden path="cities[${status.index}].cityIdn"/> 
							</td>
							<td>
								<sf:input path="cities[${status.index}].cityDesc" size="15" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div id="submitDiv" align="center" style="clear:both; width: 100%; height:50px; vertical-align: middle;">
				<input type="submit" class="btn btn-default" value="Zapisz zmiany"/>
			</div>
		</fieldset>
		</sf:form>
		
</jsp:root>