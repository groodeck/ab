<jsp:root 
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0" 
 	xmlns:c="http://java.sun.com/jsp/jstl/core"
 	>
   
    <jsp:directive.page language="java"
        contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
        />
     
    <script type="text/javascript" language="javascript">
     
    	function submitForm(event){
     		document.getElementById('event').value = event;
     		document.forms[0].submit();
     	}	
	
    </script>
 
 <html xmlns="http://www.w3.org/1999/xhtml">
 <body onload="document.forms.loginForm.login.focus()">   
    
    <form method="post" action="login.app" enctype="application/x-www-form-urlencoded" accept-charset="UTF-8"
    	name="loginForm">
	<input type="hidden" id="event" name="event" />
	
		<table style="font-family:sans-serif;" >
			<tr >
				<td height="10px"/>
			</tr><tr>
	  			<td width="100%" colspan="2">
					STRONA DOMOWA
				</td>
			</tr><tr >
				<td height="5px"/>
			</tr>
		</table>
	
	</form>

</body>
</html>
	
</jsp:root>