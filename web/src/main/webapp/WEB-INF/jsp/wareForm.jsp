<%@page contentType="text/html" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<title>Ware anlegen</title>
	</head>
<body>
<p>
<h2>Ware anlegen</h2>
<form action="./springbuchweb/Ware" method="POST">
  <b>Bezeichnung:</b>
    <br><input type="text" maxlength="30" size="30" name="bezeichnung" />
  <p>
  <b>Preis:</b>
    <br><input type="text" maxlength="30" size="30" name="preis"  />
  <p><input type="hidden" name="method" value="create">
  <input type = "submit" value="Ware erzeugen"/>
</form>
<p>
<br>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
