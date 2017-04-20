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
