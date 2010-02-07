<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
	<head>
		<title>Ware</title>
	</head>
<body>
<P>
<H2>Ware</H2>

<TABLE border="1">
  <TH>Id</TH><TH>Bezeichnung</TH><TH>Preis</TH>
   <c:forEach var="ware" items="${wareListe}">
    <TR>
      <TD><c:out value="${ware.id}"/></TD>
      <TD><c:out value="${ware.bezeichnung}"/></TD>
      <TD><c:out value="${ware.preis}"/></TD>
    </TR>
    </c:forEach>
</TABLE>  

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
