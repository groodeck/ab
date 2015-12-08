<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	 xmlns:c="http://java.sun.com/jsp/jstl/core" 
	 xmlns:fn="http://java.sun.com/jsp/jstl/functions" >

	<jsp:directive.tag language="java" pageEncoding="ISO-8859-2"/>
		
	<jsp:directive.attribute name="page" type="org.ab.ui.ResultPage" required="true"/>
	<jsp:directive.attribute name="pageChangeUrl" type="String" required="true"/>
	 
	<nav>
		<ul class="pager">
			<c:if test="${page.pageNo gt 0}">
				<li><a href="${pageChangeUrl}${page.pageNo - 1}">Previous</a></li>
			</c:if>
		   	<li><input id="pageNumber" value="${page.pageNo + 1}" style="width: 25px;" onkeydown=""/>/${page.pageCount}</li>
			<c:if test="${page.pageNo lt page.pageCount - 1}">
				<li><a href="${pageChangeUrl}${page.pageNo + 1}">Next</a></li>
			</c:if>
		</ul>
	</nav>
	<jsp:text>
		<![CDATA[
			<script type="text/javascript">

				$(document).on('keypress', 'input', function(e) {
					if(e.keyCode == 13) {
					    e.preventDefault();
					    var pageNumber = $("#pageNumber").val();
					    if(!isNaN(pageNumber) && pageNumber > 0 && pageNumber <= ${page.pageCount}){
					    	window.location.href = '${pageChangeUrl}' + (pageNumber-1);
					    }
					  }
				});

			</script>
		]]>
	</jsp:text>
	
</jsp:root>