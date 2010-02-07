<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
<head>
<title>Bestellung: Kunde eingeben</title>
</head>
<body>
<h2>Bestellung: Kunde eingeben</h2>
<spring:bind path="einfacheBestellung">
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B> </FONT>
</spring:bind>
Bitte geben Sie die Id des Kunden ein:
<form method="POST"><B>Id: </B> <spring:bind
	path="einfacheBestellung.id_kunde">
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B> </FONT>
	<BR>
	<input type="text" maxlength="10" size="10" name="id_kunde"
		value="<c:out value="${status.value}"/>" />
</spring:bind>
<P><input type="submit" value="Next" name="_target1" />
<P><input type="submit" value="Cancel" name="_cancel" />
</FORM>
<P><BR>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>