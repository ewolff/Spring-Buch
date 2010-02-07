<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
	<head>
		<title>Kunde</title>
	</head>
<body>
<p>
<h2>Kunden Liste</h2>

<table border="1">
  <th>Id</th><th>Vorname</th><th>Name</th>
  <c:forEach var="kunde" items="${kundenListe}">
    <tr>
      <td><c:out value="${kunde.id}"/></td>
      <td><c:out value="${kunde.vorname}"/></td>
      <td><c:out value="${kunde.name}"/></td>
    </tr>
  </c:forEach>
</table>



<%@ include file="/WEB-INF/jsp/footer.jsp" %>
