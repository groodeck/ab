<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<html>
	<body>	
		<table border='1' style='font-size: 12px; font-family: verdana;'>
			<tr style='text-align:center; vertical-align: middle; font-size: 16px; font-weight: bold;'>
				<td width='200' height='60'></td>
				<td colspan='6' width='250'>FAKTURA VAT</td>
				<td colspan='4' width='180'>${invoiceNumber}</td>
			</tr>
			<tr>
				<td height='25'>Data sprzeda¿y: ${receiveDate!}</td>
				<td colspan='6'></td>
				<td colspan='4' rowspan='3'>
					${seller.name!}<br/>
					${seller.addressCity!}<br/>
					${seller.addressStreet!}<br/>
				</td>
			</tr>
			<tr>
				<td height='25'>Data wystawienia: ${createDate!}</td>
				<td colspan='6'/>
			</tr>
			<tr>
				<td height="25"/>
				<td colspan='6'/>
			</tr>
			<tr>
				<td colspan='11' height='60'> </td>
			</tr>
			<tr>
				<td colspan='2' height='25'>Sprzedawca:</td>
				<td colspan='2'>Nabywca:</td>
				<td colspan='2'>Nr abonenta:</td>
				<td colspan='5'>xxx</td>
			</tr>
			<tr>
				<td colspan='2' rowspan='3'>
					${seller.name!}<br/>
					${seller.addressCity!}<br/>
					${seller.addressStreet!}<br/>
				</td>
				<td colspan='9' rowspan='3'>
					${buyer.name!}<br/>
					${buyer.addressStreet!}<br/>
					${buyer.addressCity!}
				</td>
			</tr>
			<tr/>
			<tr/>
			<tr style='vertical-align: bottom;'>
				<td colspan='2' height='40'>NIP: ${seller.nip!}</td>
				<td colspan='9'>NIP/PESEL: ${buyer.nip!}</td>
			</tr>
			<tr style='vertical-align: bottom;'>
				<td colspan='2' height='100'>
					Nr rachunku: ING Bank Œl¹ski<br/>
					76 1050 1966 1000 0092 0446 25367
				</td>
				<td colspan='9'>
					Sposób zap³aty:<br/>
					Przelew 14 dni. Termin p³atnoœci: xxx
				</td>
			</tr>
			<tr>
				<td colspan='11' height='25'/>
			</tr>
			
			<tr align="center">
				<td rowspan='2' height='25'>Nazwa us³ugi</td>
				<td rowspan='2'>Iloœæ</td>
				<td rowspan='2' colspan="2">Okres</td>
				<td rowspan='2' colspan="2">Wartoœæ netto</td>
				<td colspan='3'>VAT</td>
				<td rowspan='2' colspan='2'>Wartoœæ brutto</td>
			</tr>
			<tr>
				<td>%</td>
				<td colspan='2'>kwota</td>
			</tr>
			
			<#list serviceRecords as serviceRecord>
			<tr>
				<td height='25'>${serviceRecord.serviceName}</td>
				<td>${serviceRecord.quantity}</td>
				<td colspan="2">-xxx-</td>
				<td colspan="2">${serviceRecord.netAmount?string["0.00"]}</td>
				<td>xx</td>
				<td colspan='2'>${serviceRecord.vatAmount?string["0.00"]}</td>
				<td colspan='2'>${serviceRecord.grossAmount?string["0.00"]}</td>
			</tr>
			</#list>
			
			<tr>
				<td colspan='2' height='25'/>
				<td colspan='2'>RAZEM</td>
				<td colspan='2'>${netAmount}</td>
				<td>x</td>
				<td colspan='2'>${vatAmount}</td>
				<td colspan='2'>${grossAmount}</td>
			</tr>
			<!-- <tr>
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
			</tr> -->
			<tr>
				<td colspan='2' height='25'/>
				<td colspan='9' rowspan="2">S³ownie: ${grossAmountWords}</td>
			</tr>
			<tr>
				<td colspan='2' height='25'/>
			</tr>
			<tr>
				<td colspan='11' height='25'/>
			</tr>
			
			<tr>
				<td colspan='11' height='50'/>
			</tr>
			
			<tr style='font-size: 14px; font-style: italic; font-weight: bold;'>
				<td rowspan='2' height='50'>Razem do zap³aty:</td>
				<td rowspan='2' height='50'>${grossAmount}</td>
				<td colspan='9' rowspan='2'></td>
			</tr>
			<tr/>
			
			<tr>
				<td colspan='11' height='25'/>
			</tr>
			<tr>
				<td colspan='11' height='25'/>
			</tr>
			<tr>
				<td colspan='11' height='25'/>
			</tr>
			
			<tr style='font-size: 14px; font-style: italic; font-weight: bold;'>
				<td rowspan='2' height='50'>Saldo na dzieñ<br/>wystawienia faktury:</td>
				<td rowspan='2' height='50'>123,00</td>
				<td colspan='9' rowspan='2'></td>
			</tr>
			<tr/>
			<tr>
				<td colspan='11' height='25'>Je¿eli wartoœæ salda konta jest wy¿sza ni¿ kwota do zap³aty, pilnie skontaktuj siê z operatorem !</td>
			</tr>
			
		</table>
		
	</body>
</html>