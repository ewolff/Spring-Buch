<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
	<head>
		<title>Name</title>
	</head>
<body>
<P>
<H2>Name</H2>
<spring:bind path="name">
  <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
</spring:bind>
<form method="POST">
  <B>Name: </B>
  <spring:bind path="name">
    <FONT color="red">
      <B><c:out value="${status.errorMessage}"/></B>
    </FONT>
    <BR><input type="text" maxlength="20" size="20" name="name" value="<c:out value="${status.value}"/>" />
  </spring:bind>
  <P>
  <input type = "submit" value="Submit"/>
</FORM>
<P>
<BR>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
