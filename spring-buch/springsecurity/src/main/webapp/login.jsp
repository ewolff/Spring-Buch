<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login</h1>

G&uuml;ltige Nutzer:
<br>
<br>
wolff, Passwort: wolff, Rollen User und Admin
<br>
spring, Passwort: spring, Rolle User
<br>
<br>

<c:if test="${!empty param.login_error}">
	<font color="red"> Fehler beim Login.<BR>

	Grund: <%=((Exception) session
										.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
										.getMessage()%>
	<BR>
	</font>

</c:if>
<form action="<c:url value='j_spring_security_check'/>" method="post">
Benutzer: <input type="text" name="j_username"><br />
Passwort: <input type="text" name="j_password"><br />
<input type="submit" value="Login"></form>
</body>
</html>