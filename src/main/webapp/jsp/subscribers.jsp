<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form">
   	<jsp:directive.page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"/>
     
 <html xmlns="http://www.w3.org/1999/xhtml">
 
 <body> 
   <h2>Abonenci</h2>
   
		<table style="font-family:sans-serif;" >
			<tr>
				<td>
					<sf:form method="get" action="/subscribers/search">
						Znajdź abonenta: 
						<input name="searchPhrase" value="${searchPhrase}"/>
						<input type="submit" value="Znajdź"/>
					</sf:form>
				</td>
				<td width="20"/>
				<td>
					<a href="/subscriber/new"><button>+ Dodaj</button></a>
				</td>
			</tr>
		</table>
		
		
	
	
</body>
</html>
	
</jsp:root>