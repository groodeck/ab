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
	 		generateInvoices = function (){
	 		alert('jestem');
	 			var month = $('#month').val()
	 			var year = $('#year').val()
	 			window.location.href = '/invoices/generate/'+year+'/'+month;
	 		}	
	 		
	 		
	 	</script>
 	]]>
 </jsp:text>
 
 <body> 
   
   <custom:message uiMessage="${uiMessage}"/>
   
   <h2>Faktury</h2>
   
		<table style="font-family:sans-serif;" >
			<tr>
				<td>
					<sf:form method="get" action="/invoices/search">
						Znajdź fakturę: 
						<input name="searchPhrase" value="${searchPhrase}"/>
						<input type="submit" value="Znajdź"/>
					</sf:form>
				</td>
				<td width="30"/>
				<td>
					<sf:form method="post" commandName="generationParams" action="/invoices/generate">
						Miesiąc: <sf:select path="month" items="${months}" id="month" />
						Rok: <sf:select path="year" items="${years}" id="year" />
						<button>Generuj faktury</button>
					</sf:form>
				</td>
			</tr>
		</table>
		<br/>
		
		<c:if test="${not empty invoices}">
			<table border="1" cellspacing="0" cellpadding="2">
				<tr>
					<td>lp</td>
					<td>Data podpisania umowy</td>
					<td>Abonent</td>
					<td>Adres</td>
					<td>Miejscowość</td>
					<td>Telefon</td>
					<td>Data zakończenia umowy</td>
					<td>Przedstawiciel handlowy</td>
				</tr>
				<c:forEach var="subscriber" items="${subscribers}" varStatus="status" >
					<tr>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${status.index + 1}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.contractSignDate}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.effectiveName}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.mainAddress.streetDetails}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.mainAddress.city}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.phoneList}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.contractEndDate}"/></td>
						<td onclick="editSubscriber(${subscriber.subscriberId})"><c:out value="${subscriber.currentContract.user}"/></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	
		<div style="border: solid;">
			<c:out value="${invoice}" escapeXml="false"/>
		</div>
		
		<table border='1'>
			<tr>
				<td rowspan='3' colspan='3' width='250'>
				
				</td>
				<td colspan='7' width='200'><h2>FAKTURA VAT</h2></td>
				<td colspan='4' width='150'>1/2/2014</td>
			</tr>
			<tr>
				<td colspan='7'>Data wystawienia</td>
				<td colspan='4'>ORYGINAŁ</td>
			</tr>
			<tr>
				<td colspan='7'>Data sprzedaży</td>
				<td colspan='4'/>
			</tr>
			
			<tr>
				<td colspan='3' align="center">pieczęć sprzedawcy</td>
				<td colspan='11'/>
			</tr>
			<tr>
				<td colspan='7'>Sprzedawca</td>
				<td colspan='7'>Nabywca</td>
			</tr>
			<tr>
				<td colspan='7' rowspan='3'>
					jawor net<br/>
					00-100 Sierpc<br/>
					Łowicka 8/15
				</td>
				<td colspan='7' rowspan='3'>
					Jan Kowalski<br/>
					00-100 Sierpc<br/>
					Narutowicza 2
				</td>
			</tr>
			<tr/>
			<tr/>
			<tr>
				<td colspan='7'>NIP:</td>
				<td colspan='7'>NIP:</td>
			</tr>
			
			<tr>
				<td colspan='7' height='30'>Nr rachunku:</td>
				<td colspan='7'>Sposób zapłaty:</td>
			</tr>
			<tr>
				<td colspan='14' height='5'/>
			</tr>
			
			<tr>
				<td rowspan='3'>Lp.</td>
				<td rowspan='3'>Nazwa towaru lub usługi</td>
				<td rowspan='3'>PKWiU</td>
				<td rowspan='3'>J.m.</td>
				<td rowspan='3'>Ilość</td>
				<td rowspan='2' colspan='2'>Cena jednostkowa bez podatku</td>
				<td rowspan='2' colspan='2'>Wartość netto</td>
				<td colspan='3'>Podatek</td>
				<td rowspan='2' colspan='2'>Wartość brutto</td>
			</tr>
			<tr>
				<td>%</td>
				<td colspan='2'>kwota</td>
			</tr>
			<tr>
				<td>zł</td>
				<td>gr</td>
				<td>zł</td>
				<td>gr</td>
				<td/>
				<td>zł</td>
				<td>gr</td>
				<td>zł</td>
				<td>gr</td>
			</tr>
			<tr>
				<td>1</td>
				<td/>
				<td/>
				<td/>
				<td/>
				<td colspan='2'/>
				<td colspan='2'/>
				<td/>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			<tr>
				<td colspan='5'/>
				<td colspan='2'>RAZEM</td>
				<td colspan='2'/>
				<td>x</td>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			<tr>
				<td colspan='5'/>
				<td colspan='2' rowspan='5'>W TYM</td>
				<td colspan='2'/>
				<td>zw</td>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			<tr>
				<td colspan='5'/>
				<td colspan='2'/>
				<td>23</td>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			<tr>
				<td colspan='5'>Słownie:</td>
				<td colspan='2'/>
				<td>8</td>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			<tr>
				<td colspan='5' rowspan='2'/>
				<td colspan='2'/>
				<td>5</td>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			<tr>
				<td colspan='2'/>
				<td>0</td>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			
			<tr>
				<td colspan='14' height='5'/>
			</tr>
			
			<tr>
				<td colspan='5' rowspan='2' height='40'>Razem do zapłaty:</td>
				<td colspan='9' rowspan='2'></td>
			</tr>
			<tr/>
			
			<tr>
				<td colspan='14' height='5'/>
			</tr>
			<tr>
				<td colspan='14' rowspan='2' height='40' valign="top">Uwagi:</td>
			</tr>
			<tr/>
			
			<tr>
				<td colspan='14' height='5'/>
			</tr>
			
			<tr>
				<td/>
				<td colspan='2' rowspan='4' height='80' />
				<td/>
				<td/>
				<td/>
				<td colspan='7' rowspan='4' height='80' />
				<td/>
			</tr>
			<tr>
				<td/>
				<td/>
				<td/>
				<td/>
				<td/>
			</tr>
			<tr>
				<td/>
				<td/>
				<td/>
				<td/>
				<td/>
			</tr>
			<tr>
				<td/>
				<td/>
				<td/>
				<td/>
				<td/>
			</tr>
			<tr>
				<td/>
				<td colspan='2' style="font-size: 10px">Podpis imienny osoby upoważnionej do odbioru faktur VAT</td>
				<td/>
				<td/>
				<td/>
				<td colspan='7' style="font-size: 10px">Podpis imienny osoby upoważnionej do wystawienia faktury VAT</td>
				<td/>
			</tr>
			
		</table>		
		
	
</body>
</html>
	
</jsp:root>