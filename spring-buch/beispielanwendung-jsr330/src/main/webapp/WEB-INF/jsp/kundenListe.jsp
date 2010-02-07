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
<title>Kunden</title>
</head>
<body>
<h2>Kunden</h2>
<table border="1">
<thead>
	<th>Id</th>
	<th>Vorname</th>
	<th>Name</th>
	<th>Edit</th>
	</thead>
	<c:forEach var="kunde" items="${kundenListe}">
		<tr>
			<td><c:out value="${kunde.id}" /></td>
			<td><c:out value="${kunde.vorname}" /></td>
			<td><c:out value="${kunde.name}" /></td>
			<td><a href="<spring:url value="/springbuchweb/kunden/${kunde.id}.html"/>">Edit</a></td>
			<td><a href="<spring:url value="/springbuchweb/kunden/${kunde.id}.xml"/>">XML</a></td>
		</tr>
	</c:forEach>
</table>
<a href="<spring:url value="/"/>">Home</a>
</body>
</html>
