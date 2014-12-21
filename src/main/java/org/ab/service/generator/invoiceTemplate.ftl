	<table border='1'>
			<tr>
				<td rowspan='3' colspan='3' width='250'>
				
				</td>
				<td colspan='7' width='200'><h2>FAKTURA VAT</h2></td>
				<td colspan='4' width='150'>${invoiceNumber}</td>
			</tr>
			<tr>
				<td colspan='7'>Data wystawienia: ${createDate!}</td>
				<td colspan='4'>ORYGINA£</td>
			</tr>
			<tr>
				<td colspan='7'>Data sprzeda¿y: ${receiveDate!}</td>
				<td colspan='4'/>
			</tr>
			
			<tr>
				<td colspan='3' align="center">pieczêæ sprzedawcy</td>
				<td colspan='11'/>
			</tr>
			<tr>
				<td colspan='7'>Sprzedawca</td>
				<td colspan='7'>Nabywca</td>
			</tr>
			<tr>
				<td colspan='7' rowspan='3'>
					${seller.name!}<br/>
					${seller.addressStreet!}<br/>
					${seller.addressCity!}<br/>
					${seller.regon!}
				</td>
				<td colspan='7' rowspan='3'>
					${buyer.name!}<br/>
					${buyer.addressStreet!}<br/>
					${buyer.addressCity!}
				</td>
			</tr>
			<tr/>
			<tr/>
			<tr>
				<td colspan='7'>NIP: ${seller.nip!}</td>
				<td colspan='7'>NIP: ${buyer.nip!}</td>
			</tr>
			
			<tr>
				<td colspan='7' height='30'>Nr rachunku:</td>
				<td colspan='7'>Sposób zap³aty:</td>
			</tr>
			<tr>
				<td colspan='14' height='5'/>
			</tr>
			
			<tr>
				<td rowspan='3'>Lp.</td>
				<td rowspan='3'>Nazwa towaru lub us³ugi</td>
				<td rowspan='3'>PKWiU</td>
				<td rowspan='3'>J.m.</td>
				<td rowspan='3'>Iloœæ</td>
				<td rowspan='2' colspan='2'>Cena jednostkowa bez podatku</td>
				<td rowspan='2' colspan='2'>Wartoœæ netto</td>
				<td colspan='3'>Podatek</td>
				<td rowspan='2' colspan='2'>Wartoœæ brutto</td>
			</tr>
			<tr>
				<td>%</td>
				<td colspan='2'>kwota</td>
			</tr>
			<tr>
				<td>z³</td>
				<td>gr</td>
				<td>z³</td>
				<td>gr</td>
				<td/>
				<td>z³</td>
				<td>gr</td>
				<td>z³</td>
				<td>gr</td>
			</tr>
			<#list serviceRecords as serviceRecord>
				<tr>
					<td>${serviceRecord.lp}</td>
					<td>${serviceRecord.serviceName}</td>
					<td/>
					<td/>
					<td>${serviceRecord.quantity}</td>
					<td colspan='2'>${serviceRecord.netAmount?string["0.00"]}</td>
					<td colspan='2'/>
					<td/>
					<td colspan='2'>${serviceRecord.vatAmount?string["0.00"]}</td>
					<td colspan='2'>${serviceRecord.grossAmount?string["0.00"]}</td>
				</tr>
			</#list>
			<tr>
				<td colspan='5'/>
				<td colspan='2'>RAZEM</td>
				<td colspan='2'>${netAmount}</td>
				<td>x</td>
				<td colspan='2'>${vatAmount}</td>
				<td colspan='2'>${grossAmount}</td>
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
				<td colspan='5'>S³ownie:</td>
				<td colspan='2'/>
				<td>8</td>
				<td colspan='2'/>
				<td colspan='2'/>
			</tr>
			<tr>
				<td colspan='5' rowspan='2'>${grossAmountWords}</td>
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
				<td colspan='2' rowspan='2' height='40'>Razem do zap³aty:</td>
				<td colspan='3' rowspan='2' height='40'>${grossAmount}</td>
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
				<td colspan='2' style="font-size: 10px">Podpis imienny osoby upowa¿nionej do odbioru faktur VAT</td>
				<td/>
				<td/>
				<td/>
				<td colspan='7' style="font-size: 10px">Podpis imienny osoby upowa¿nionej do wystawienia faktury VAT</td>
				<td/>
			</tr>
			
		</table>