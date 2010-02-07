<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Kunden Id eingeben</title>
</head>
<body>
<h2>Kunden Id eingeben</h2>
<p />
G&uuml;ltig: ID ${kunde.id}
<p />
<form:form modelAttribute="einkaufswagen">
	<b>Bitte geben Sie ihre Kunden Id ein:</b>
	<input type="hidden" name="_flowExecutionId"
		value="<c:out value="${flowExecutionId}"/>" />
	<br />
	<font color="red"> <b>${status.errorMessage}</b> </font>
	<spring:bind path="id_Kunde">
		<font color="red"> <b><c:out value="${status.errorMessage}" /></b>
		</font>
		<input type="text" maxlength="10" size="10" name="id_Kunde"
			value="<c:out value="${status.value}" />" />
	</spring:bind>

	<p />
	<input type="submit" value="Submit" name="_eventId_submit" />
</form:form>
<p />
<br />

</body>
</html>