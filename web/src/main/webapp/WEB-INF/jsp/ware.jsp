<%@ page contentType="text/html"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Ware</title>
</head>
<body>
<p>
<h2>Ware</h2>
<form action="springbuchweb/Ware" method="POST"><b>Bezeichnung:</b>
<br />
<input type="text" maxlength="30" size="30" name="name" />
<p><input type="hidden" name="method" value="deleteByBezeichnung">
<input type="submit" value="Ware l&ouml;schen" />
</form>

<form action="springbuchweb/Ware" method="POST"><b>Id:</b> <br />
<input type="text" maxlength="30" size="30" name="id" />
<p><input type="hidden" name="method" value="deleteByID"> <input
	type="submit" value="Ware l&ouml;schen" />
</form>

<form action="springbuchweb/Ware" method="POST"><b>Bezeichnung:</b>
<br />
<input type="text" maxlength="30" size="30" name="name" />
<p><input type="hidden" name="method" value="getByBezeichnung">
<input type="submit" value="Ware anzeigen" />
</form>
<form action="springbuchweb/Ware" method="POST"><b>Id:</b> <br />
<input type="text" maxlength="30" size="30" name="id" />
<p><input type="hidden" name="method" value="getByID"> <input
	type="submit" value="Ware anzeigen" />
</form>
<br />
<a href="springbuchweb/Ware?method=getAll">Alle Waren anzeigen</a>
<p><br />

<%@ include file="/WEB-INF/jsp/footer.jsp"%>