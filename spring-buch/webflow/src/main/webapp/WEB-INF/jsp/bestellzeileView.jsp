<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<html>
<head>
<title>Einkaufswagen</title>
</head>
<body>
<h2>Einkaufswagen</h2>
<table border="1">
	<c:if test="${!empty einkaufswagen.inhalt}">
		<tr>
			<th>Anzahl</th>
			<th>Ware</th>
		</tr>
		<c:forEach var="zeile" items="${einkaufswagen.inhalt}">
			<tr>
				<td><c:out value="${zeile.anzahl}" /></td>
				<td><c:out value="${zeile.id_Ware}" /></td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<p>G&uuml;ltig: ID ${ware.id}
<p>
<form method="POST"><input type="hidden" name="_flowExecutionId"
	value="<c:out value="${flowExecutionId}"/>" /> <BR />

<spring:bind path="bestellzeile">
	<font color="red"> <B><c:out value="${status.errorMessage}" /></B>
	</font>
</spring:bind>

<table>
	<tr>
		<spring:bind path="bestellzeile.anzahl">
			<td>Anzahl:</td>
			<td><FONT color="red"> <B><c:out
				value="${status.errorMessage}" /></B> </FONT> <input type="text"
				maxlength="10" size="10" name="anzahl"
				value="<c:out value="${status.value}" />" /></td>
		</spring:bind>
	</tr>

	<tr>
		<spring:bind path="bestellzeile.id_Ware">
			<td>ID Ware:</td>
			<td><FONT color="red"> <B><c:out
				value="${status.errorMessage}" /></B> </FONT> <input type="text"
				maxlength="10" size="10" name="id_Ware"
				value="<c:out value="${status.value}" />" /></td>
		</spring:bind>
	</tr>

	<tr>
		<td><input type="submit" value="Bestellung abschicken"
			name="_eventId_abschicken" /></td>
		<td><input type="submit" value="Zeile hinzufuegen"
			name="_eventId_hinzufuegen" /></td>
	</tr>

</table>

</form>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>