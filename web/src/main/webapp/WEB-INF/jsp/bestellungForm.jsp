<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<html>
<head>
<title>Bestellung: Kunde eingeben</title>
</head>
<body>
<h2>Bestellung: Kunde eingeben</h2>
<form:form method="POST" commandName="bestellung"><font color="red"> <form:errors
	path="*" /> </font> Bitte geben Sie die Id des Kunden ein: <br /> <b>Id:</b> <font
	color="red"> <b><form:errors path="id_kunde" /></b> </font> <br>
<form:input size="10" path="id_kunde" /> <br />
<br />
Bitte geben Sie die Id und Anzahl der Ware ein. <br />
<b>Id:</b> <font color="red"> <b><form:errors path="id_ware" /></b>
</font> <br>
<form:input size="10" path="id_ware" /> <br>
<b>Anzahl: </b> <font color="red"> <b><form:errors
	path="anzahl" /></b> </font> <br>
<form:input size="10" path="anzahl" /> <br>


<P><input type="submit" value="Submit"  />
</form:form>
<P><BR>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>