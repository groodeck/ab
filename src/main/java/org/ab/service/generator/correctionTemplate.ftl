<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<html>
	<body>	
		<table cellspacing='0' style='font-size: 11px; font-family: arial;'>
			<tr style='text-align:center; vertical-align: middle; font-size: 15px; font-weight: bold;'>
				<td width='230' height='60'></td>
				<td colspan='6' width='200'>KOREKTA</td>
				<td colspan='4' width='220' style='text-align:left;'>${correctionNumber}</td>
			</tr>
			<tr>
				<td height='22'></td>
                <td colspan='6' width='200'></td>
				<td colspan='4' width='220' style='text-align:left;'>Do faktury:<br/>${invoice.invoiceNumber}</td>
			</tr>
			<tr>
				<td height='22'>Data sprzeda¿y: ${receiveDate!}</td>
				<td colspan='6'></td>
				<td colspan='4' rowspan='3'>
					${invoice.seller.name!}<br/>
					${invoice.seller.addressCity!}<br/>
					${invoice.seller.addressStreet!}<br/>
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
				<td colspan='2' width='80'>Nr abonenta:</td>
				<td colspan='5'>${invoice.subscriberIdn!}</td> 	
			</tr>
			<tr>
				<td colspan='2' rowspan='3'>
					${invoice.seller.name!}<br/>
					${invoice.seller.addressCity!}<br/>
					${invoice.seller.addressStreet!}<br/>
				</td>
				<td colspan='9' rowspan='3'>
					${invoice.subscriber.name!}<br/>
					${invoice.subscriber.addressStreet!}<br/>
					${invoice.subscriber.addressCity!}
				</td>
			</tr>
			<tr/>
			<tr/>
			<tr style='vertical-align: bottom;'>
				<td colspan='2' height='40' style='border-bottom-width: 1px; border-bottom-style: solid;'>
					NIP: ${invoice.seller.nip!}
				</td>
				<td colspan='9' style='border-bottom-width: 1px; border-bottom-style: solid;'>
					NIP/PESEL: ${invoice.subscriber.nip!}
				</td>
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
			
			<tr style='text-align:center;'>
				<td rowspan='2' height='22' style='border: 1px solid;'>
					Nazwa us³ugi
				</td>
				<td rowspan='2' width='60' style='border-top-width: 1px; border-top-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;' />
				<td rowspan='2' width='70' style='border-top-width: 1px; border-top-style: solid; border-bottom-width: 1px; border-bottom-style: solid;'>
					Iloœæ
				</td>
				<td rowspan='2' style='border: 1px solid;'>
					Okres
				</td>
				<td rowspan='2' colspan="2" style='border-top-width: 1px; border-top-style: solid; border-bottom-width: 1px; border-bottom-style: solid;'>
					Wartoœæ netto
				</td>
				<td colspan='3' width='90' style='border: 1px solid;'>
					VAT
				</td>
				<td rowspan='2' colspan='2' width='90' style='border-top-width: 1px; border-top-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					Wartoœæ brutto
				</td>
			</tr>
			<tr style='text-align:center;'>
				<td style='border-bottom-width: 1px; border-bottom-style: solid; border-left-width: 1px; border-left-style: solid; border-right-width: 1px; border-right-style: solid;'>
					%
				</td>
				<td colspan='2' style='border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					kwota
				</td>
			</tr>
			
			<#list serviceRecords as serviceRecord>
			<tr>
				<td height='22'  style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.invoiceRecord.serviceName}
				</td>
				<td style='border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					Przed korekt¹
				</td>
				<td style='border-bottom-width: 1px; border-bottom-style: solid;'>
					${serviceRecord.invoiceRecord.quantity}
				</td>
				<td width='160' style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${invoice.settlementPeriodStart!}-${invoice.settlementPeriodEnd!}
				</td>
				<td colspan="2" style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid;'>
					${serviceRecord.invoiceRecord.netAmount?string["0.00"]}
				</td>
				<td style='text-align:right; border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.invoiceRecord.vatRate!}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.invoiceRecord.vatAmount?string["0.00"]}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.invoiceRecord.grossAmount?string["0.00"]}
				</td>
			</tr>
			
			<tr>
				<td height='22' rowspan="2"  style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.serviceName}
				</td>
				<td style='border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					Po korekcie
				</td>
				<td style='border-bottom-width: 1px; border-bottom-style: solid;'>
					${serviceRecord.quantity}
				</td>
				<td style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
				
				</td>
				<td colspan="2" style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid;'>
					${serviceRecord.netAmount?string["0.00"]}
				</td>
				<td style='text-align:right; border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.vatRate!}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.vatAmount?string["0.00"]}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.grossAmount?string["0.00"]}
				</td>
			</tr>
			
			<tr>
				<td style='border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					Korekta
				</td>
				<td style='border-bottom-width: 1px; border-bottom-style: solid;'>
					${serviceRecord.quantityDiff}
				</td>
				<td style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
				
				</td>
				<td colspan="2" style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid;'>
					${serviceRecord.netAmountDiff?string["0.00"]}
				</td>
				<td style='text-align:right; border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.vatRate!}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.vatAmountDiff?string["0.00"]}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${serviceRecord.grossAmountDiff?string["0.00"]}
				</td>
			</tr>
			</#list>
			
			<tr>
				<td colspan='3' height='25'/>
				<td  style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					RAZEM PRZED KOREKT¥
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid;'>
					${invoice.netAmount?string["0.00"]}
				</td>
				<td style='text-align:center; border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					x
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${invoice.vatAmount?string["0.00"]}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${invoice.grossAmount?string["0.00"]}
				</td>
			</tr>
			
			<tr>
				<td colspan='3' height='22'/>
				<td style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					RAZEM PO KOREKCIE
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid;'>
					${netAmount?string["0.00"]}
				</td>
				<td style='text-align:center; border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					x
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${vatAmount?string["0.00"]}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${grossAmount?string["0.00"]}
				</td>
			</tr>
			
			<tr>
				<td colspan='3' height='22'/>
				<td style='border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					KOREKTA
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid;'>
					${netAmountDiff?string["0.00"]}
				</td>
				<td style='text-align:center; border-left-width: 1px; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					x
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${vatAmountDiff?string["0.00"]}
				</td>
				<td colspan='2' style='text-align:right; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${grossAmountDiff?string["0.00"]}
				</td>
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
				<td colspan='9' rowspan="2">S³ownie: ${grossAmountDiffWords}</td>
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
				<td rowspan='2' height='50' style='border: 1px solid;'>
					Razem do zap³aty:
				</td>
				<td rowspan='2' height='50' style='border-top-width: 1px; border-top-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					${grossAmountDiff?string["0.00"]}
				</td>
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
				<td rowspan='2' height='50' style='border: 1px solid;'>
					Saldo na dzieñ<br/>wystawienia faktury:
				</td>
				<td rowspan='2' height='50' style='border-top-width: 1px; border-top-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-right-width: 1px; border-right-style: solid;'>
					0,00
				</td>
				<td colspan='9' rowspan='2'></td>
			</tr>
			<tr/>
			<tr>
				<td colspan='11' height='22'>Je¿eli wartoœæ salda konta jest wy¿sza ni¿ kwota do zap³aty, pilnie skontaktuj siê z operatorem !</td>
			</tr>
			
		</table>
		
	</body>
</html>