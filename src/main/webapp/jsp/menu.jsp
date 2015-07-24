<?xml version="1.0" encoding="UTF8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:security="http://www.springframework.org/security/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core">

	<span> 
		<security:authorize access="isAuthenticated()"> 
			<ul class="nav nav-pills">
				<li role="presentation"><a href="/subscribers">Abonenci</a></li>
				<li role="presentation"><a href="/invoices">Faktury</a></li>
				<li role="presentation"><a href="/payments">Wpłaty</a></li>
				<li class="dropdown">
			  		<a href="#" data-toggle="dropdown" class="dropdown-toggle">Ustawienia<!-- <b class="caret"/> --></a>
		        	<ul class="dropdown-menu">
		            	<li><a href="/packages">Pakiety</a></li>
		            	<li><a href="/cities">Miasta</a></li>
		            	<li><a href="/vatRates">Stawki VAT</a></li>
		            	<li><a href="/contractDuration">Długość umowy</a></li>
		        	</ul>
				</li>
			</ul>
			
			<br/><hr/>
		</security:authorize>
	</span>

</jsp:root>