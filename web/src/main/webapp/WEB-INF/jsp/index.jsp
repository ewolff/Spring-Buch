<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
	<head>
		<title>Spring Demo Anwendung</title>
	</head>
<body>
<p>
<h2>Spring Demo Anwendung</h2>


<p>
  <a href="<c:url value='/springbuchweb/listeEinfach.html' />">Kunden Liste</a>
</p>
<p>
  <a href="<c:url value='/springbuchweb/ListeXslt' />">Kunden Liste (XSLT Rendering)</a>
</p>
<p>
  <a href="<c:url value='/springbuchweb/ListePdf.pdf' />">Kunden Liste (Pdf)</a>
</p>
<p>
  <a href="<c:url value='/springbuchweb/ListeXls.xls' />">Kunden Liste (Excel)</a>
</p>
<p>
  <a href="<c:url value='/springbuchweb/Create' />">Kunden anlegen</a>  
</p>
<p></p>
<p>
  <a href="<c:url value='/springbuchweb/DeleteById' />">Kunden anhand der ID l&ouml;schen</a>  
</p>
<p>
  <a href="<c:url value='/springbuchweb/DeleteByName' />">Kunden anhand des Names l&ouml;schen</a>  
</p>
<p></p>
<p>
  <a href="<c:url value='/springbuchweb/FindById' />">Kunden anhand der ID suchen</a>  
</p>
<p>
  <a href="<c:url value='/springbuchweb/FindByName' />">Kunden anhand des Namens suchen</a>  
</p>
<p></p>
<p>
  <a href="<c:url value='/springbuchweb/Bestellung' />">Bestellung aufgeben</a>  
</p>
<p>
  <a href="<c:url value='/wareForm.jsp' />">Ware anlegen</a>  
</p>
<p>
  <a href="<c:url value='/ware.jsp' />">Andere Ware Aktionen</a>  
</p>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>
