<%@include file="/WEB-INF/jsp/includes.jsp"%>

<html>
<head>
<title>Kunde</title>
</head>
<body>
<P>
<H2>Kunde</H2>
<spring:bind path="kunde">
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B> </FONT>
</spring:bind>
<form method="POST"><B>Name:</B> <spring:bind path="kunde.name">
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B> </FONT>
	<BR>
	<input
		type="text"
		maxlength="30"
		size="30"
		name="name"
		value="<c:out value="${status.value}"/>" />
</spring:bind>
<P><B>Vorname:</B> <spring:bind path="kunde.vorname">
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B> </FONT>
	<BR>
	<input
		type="text"
		maxlength="30"
		size="30"
		name="vorname"
		value="<c:out value="${status.value}"/>" />
</spring:bind>
<P><B>Kontostand:</B> <spring:bind path="kunde.kontostand">
	<FONT color="red"> <B><c:out value="${status.errorMessage}" /></B> </FONT>
	<BR>
	<input
		type="text"
		maxlength="30"
		size="30"
		name="kontostand"
		value="<c:out value="${status.value}"/>" />
</spring:bind>
<P><input
	type="submit"
	value="Kunde erzeugen" />
</FORM>
<P><BR>

<spring:hasBindErrors name="kunde">
	<font color="red">Bitte korrigieren Sie die Eingaben!</font>
	<br />
</spring:hasBindErrors> <%@ include file="/WEB-INF/jsp/footer.jsp"%>