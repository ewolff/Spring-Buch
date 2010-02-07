<%@page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib
	prefix="form"
	uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Bestellung: Ware eingeben</title>
</head>
<body>
<h2>Bestellung: Ware eingeben</h2>
<form:form commandName="einfacheBestellung">
	<font color="red"> <form:errors path="*" /> </font>
Bitte geben Sie die Id und Anzahl der Ware ein. <br>
	<b>Id:</b>
	<font color="red"> <b><form:errors path="id_ware" /></b> </font>
	<br>
	<form:input
		size="10"
		path="id_ware" />
	<br>
	<b>Anzahl: </b>
	<font color="red"> <b><form:errors path="anzahl" /></b> </font>
	<br>
	<form:input
		size="10"
		path="anzahl" />
	<br>
	<input
		type="submit"
		value="Back"
		name="_target0" />
	<input
		type="submit"
		value="Finish"
		name="_finish" />
	<input
		type="submit"
		value="Cancel"
		name="_cancel" />
</form:form>
<br>
<br>
<a href="<c:url value='/index.jsp' />">Home</a>
</body>
</html>
