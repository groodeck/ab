<?xml version="1.0" encoding="UTF8" ?>
<jsp:root 
	xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:s="http://www.springframework.org/tags"
	xmlns:sf="http://www.springframework.org/tags/form">
	
	<jsp:directive.page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8" />
	
	<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF8" />
			<title>JaworNET - Abonenci</title>
			<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" /> 
			<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" />
		</head>
		
		<jsp:text>
			<![CDATA[ 
				<?xml version="1.0" encoding="UTF8" ?> 
			 	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
	
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
				<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
				<script src="/bootstrap/js/bootstrap.min.js"></script>
			 ]]>
		</jsp:text>
	 	 	
		<body style="font-family:sans-serif; margin: 10px;">
		
			<div id="banner">
				<tiles:insertAttribute name="header" />
			</div>
			<div id="menu">
				<tiles:insertAttribute name="menu" />
			</div>
			<div id="subscriberDetails">
				<tiles:insertAttribute name="subscriberDetails" />
			</div>
			<div id="page">
				<tiles:insertAttribute name="content" />
			</div>
			<div></div>
		
		</body>
	</html>
</jsp:root>