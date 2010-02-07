<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
	<head>
		<title>Exception</title>
	</head>
<body>
<p>
<h2>Exception</h2>

<b>Handler</b><br /><br />
<pre>
<c:out value="${handler}"/><br /><br />
</pre>
<b>Message</b><br /><br />
<pre>
<c:out value="${message}"/><br /><br />
</pre>
<b>Stacktrace</b> <br /><br />
<pre>
<c:out value="${stacktrace}"/><br /><br />
</pre>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
