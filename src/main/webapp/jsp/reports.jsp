<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:custom="urn:jsptagdir:/WEB-INF/tags">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
 
 <jsp:text>
 	<![CDATA[
 		<script type="text/javascript">
	 		generateReport = function (){
	 			var reportType = $("#reportType").val();
	 			window.location.href = ('/reports/generateReport/' + reportType);
	 		}	
	 	</script>
 	]]>
 </jsp:text>
 
   <custom:message uiMessage="${uiMessage}"/>
   
    <h3>Raporty</h3>
		
		<sf:form method="post" commandName="reportParameters">
		
			<div class="row">
				<div class="col-lg-4">
		            <div class="input-group">
		            	<span class="input-group-addon" id="basic-addon1">Wybierz raport:</span>
		                <sf:select class="form-control" path="${reportType}" items="${reportTypes}" id="reportType"/>
		                <span class="input-group-btn">
		                    <button class="btn btn-default" type="button" onclick="generateReport()">Generuj</button>
		                </span>
		            </div>
		        </div>
			</div>
			
		</sf:form>
		
</jsp:root>