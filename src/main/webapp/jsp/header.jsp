<?xml version="1.0" encoding="UTF8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:security="http://www.springframework.org/security/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core">

	<span> 
		<security:authorize access="isAuthenticated()"> 
		
			<table>
				<tr height="50px">
					<td>Jesteś zalogowany jako:</td>
					<td><security:authentication property="principal.Username"/></td>
					<td><a href="/j_spring_security_logout"><button>Wyloguj</button></a></td>
				</tr>
			</table>
			 
			
			
		</security:authorize>
	</span>

</jsp:root>