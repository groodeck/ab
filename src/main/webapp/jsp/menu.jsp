<?xml version="1.0" encoding="UTF8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:security="http://www.springframework.org/security/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core">

	<span> 
		<security:authorize access="isAuthenticated()"> 
		
			<table>
				<tr>
					<td align="center"><a href="/subscribers">Abonenci</a></td>
					<td align="center"><a href="/invoices">Faktury</a></td>
				</tr>
			</table>
			
		</security:authorize>
	</span>

</jsp:root>