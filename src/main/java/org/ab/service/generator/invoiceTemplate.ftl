<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
<style>
	tr.border_bottom td {
	  border-bottom:1px solid;
	}
	
	tr.bordered td {
	  border-bottom:1px solid;
	  border-left:1px solid;
	}
</style>
<html>
	<body>	
		<table style='font-size: 11px; font-family: arial;'>
			<tr style='text-align:center; vertical-align: middle; font-size: 15px; font-weight: bold;'>
				<td width='230' height='60'></td>
				<td colspan='6' width='200'>FAKTURA VAT</td>
				<td colspan='4' width='220'>${invoiceNumber}</td>
			</tr>
			<tr>
				<td height='22'>Data sprzeda¿y: ${receiveDate!}</td>
				<td colspan='6'></td>
				<td colspan='4' rowspan='3'>
					${seller.name!}<br/>
					${seller.addressCity!}<br/>
					${seller.addressStreet!}<br/>
				</td>
			</tr>
			<tr>
				<td height='22'>Data wystawienia: ${createDate!}</td>
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
				<td colspan='2' height='22'>Sprzedawca:</td>
				<td colspan='2'>Nabywca:</td>
				<td colspan='2' width='100'>Nr abonenta:</td>
				<td colspan='5'>${subscriberIdn!}</td>
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
			<tr class='border_bottom' style='vertical-align: bottom;'>
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
					Przelew 14 dni. Termin p³atnoœci: ${paymentDate!}
				</td>
			</tr>
			<tr>
				<td colspan='11' height='22'/>
			</tr>
			
			<tr class="bordered" style='text-align:center;'>
				<td rowspan='2' height='22'>Nazwa us³ugi</td>
				<td rowspan='2' width='70'>Iloœæ</td>
				<td rowspan='2' colspan="2">Okres</td>
				<td rowspan='2' colspan="2">Wartoœæ netto</td>
				<td colspan='3' width='90'>VAT</td>
				<td rowspan='2' colspan='2' width='90'>Wartoœæ brutto</td>
			</tr>
			<tr style='text-align:center;'>
				<td>%</td>
				<td colspan='2'>kwota</td>
			</tr>
			
			<#list serviceRecords as serviceRecord>
			<tr class="bordered">
				<td height='22'>${serviceRecord.serviceName}</td>
				<td>${serviceRecord.quantity}</td>
				<td colspan="2" width='160'>${settlementPeriodStart!}-${settlementPeriodEnd!}</td>
				<td colspan="2" style='text-align:right;'>${serviceRecord.netAmount?string["0.00"]}</td>
				<td>${vatRate!}</td>
				<td colspan='2' style='text-align:right;'>${serviceRecord.vatAmount?string["0.00"]}</td>
				<td colspan='2' style='text-align:right;'>${serviceRecord.grossAmount?string["0.00"]}</td>
			</tr>
			</#list>
			
			<tr>
				<td colspan='2' height='22'/>
				<td colspan='2'>RAZEM</td>
				<td colspan='2' style='text-align:right;'>${netAmount?string["0.00"]}</td>
				<td style='text-align:center;'>x</td>
				<td colspan='2' style='text-align:right;'>${vatAmount?string["0.00"]}</td>
				<td colspan='2' style='text-align:right;'>${grossAmount?string["0.00"]}</td>
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
				<td colspan='2' height='22'/>
				<td colspan='9' rowspan="2">S³ownie: ${grossAmountWords}</td>
			</tr>
			<tr>
				<td colspan='2' height='22'/>
			</tr>
			<tr>
				<td colspan='11' height='22'/>
			</tr>
			
			<tr>
				<td colspan='11' height='50'/>
			</tr>
			
			<tr style='font-size: 12px; font-style: italic; font-weight: bold;'>
				<td rowspan='2' height='50'>Razem do zap³aty:</td>
				<td rowspan='2' height='50'>${grossAmount?string["0.00"]}</td>
				<td colspan='9' rowspan='2'></td>
			</tr>
			<tr/>
			
			<tr>
				<td colspan='11' height='22'/>
			</tr>
			<tr>
				<td colspan='11' height='22'/>
			</tr>
			<tr>
				<td colspan='11' height='22'/>
			</tr>
			
			<tr style='font-size: 12px; font-style: italic; font-weight: bold;'>
				<td rowspan='2' height='50'>Saldo na dzieñ<br/>wystawienia faktury:</td>
				<td rowspan='2' height='50'>0,00</td>
				<td colspan='9' rowspan='2'></td>
			</tr>
			<tr/>
			<tr>
				<td colspan='11' height='22'>Je¿eli wartoœæ salda konta jest wy¿sza ni¿ kwota do zap³aty, pilnie skontaktuj siê z operatorem !</td>
			</tr>
			
		</table>
		
	</body>
</html>