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
		<br/>
		
		
		<sf:form method="post" action="/cities/save" modelAttribute="cities" >
		<fieldset>
			<table class="table" id="cityTable" >
				<thead>
					<tr class="tableHeader">
						<custom:sortableHeader column="${citiesTableHeader.columns.cityIdn}" sortUrl="/cities/sort/"/>
						<custom:sortableHeader column="${citiesTableHeader.columns.cityDescription}" sortUrl="/cities/sort/"/>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="city" items="${cities.records}" varStatus="status" >
						<tr>
							<td>
								<sf:input path="records[${status.index}].cityIdn" size="15" disabled="true" class="form-control"/>
								<sf:hidden path="records[${status.index}].cityIdn"/> 
							</td>
							<td>
								<sf:input path="records[${status.index}].cityDesc" size="15" class="form-control" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<custom:pageNav page="${cities}" pageChangeUrl="/cities/changePage/"/>
			
			<div id="submitDiv" align="center" style="clear:both; width: 100%; height:50px; vertical-align: middle;">
				<input type="submit" class="btn btn-default" value="Zapisz zmiany"/>
			</div>
		</fieldset>
		</sf:form>
		
</jsp:root>