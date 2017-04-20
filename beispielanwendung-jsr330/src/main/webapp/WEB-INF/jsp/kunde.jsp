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
<title>Kunde</title>
</head>
<body>
<h2>Kunde</h2>
<spring:bind path="kunde">
	<font color="red"> <b><c:out value="${status.errorMessage}" /></b>
	</font>
</spring:bind>
<spring:url value="/springbuchweb/kunden/{kunde-id}" htmlEscape="true"
	var="formaction">
	<spring:param name="kunde-id" value="${kunde.id}" />
</spring:url>
<form method="post" action="${formaction}"><b>Name:</b> <br />
<input type="text" maxlength="30" size="30" name="name"
	value="<c:out value="${kunde.name}"/>" />
<p />
<b>Vorname:</b> <br />
<input type="text" maxlength="30" size="30" name="vorname"
	value="<c:out value="${kunde.vorname}"/>" />
<p />
<b>Kontostand:</b> <br />
<input type="text" maxlength="30" size="30" name="kontostand"
	value="<c:out value="${kunde.kontostand}"/>" />
<p />
<input type="submit" value="Abschicken" /></form>
<form:form method="delete">
	<p class="submit"><input type="submit" value="L&ouml;schen" /></p>
</form:form>
<p />
<br />

<a href="<spring:url value="/"/>">Home</a>
</body>
</html>
