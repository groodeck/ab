<?xml version="1.0" encoding="UTF8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:security="http://www.springframework.org/security/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core">

	<script>
		clearSubscriberCtx = function(invoiceId){
			var subscribeRequest = $.ajax({
		   	url: "/async/clearSubscriberContext"
		});
		subscribeRequest.done(function()	{
			$("#subscriberBarDiv").hide();	
		});
		}
	</script>

	<c:if test="${sessionScope.subscriber != null}">
		<div id="subscriberBarDiv"> 
			<table cellpadding="3px"><tr valign="middle">
				<td>Wybrany abonent:</td>  
				<td><a href="/subscriber/edit/${sessionScope.subscriber.subscriberId}" style="background-color: #8EB4E6;">
		    			<c:out value="${sessionScope.subscriber.subscriberIdn} (${sessionScope.subscriber.effectiveName})"/>
					</a></td>
				<td><button onclick="clearSubscriberCtx()">Czyść</button></td>
				<td><br/></td>
			</tr></table>
		</div>
	</c:if>
</jsp:root>