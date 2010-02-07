<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html>
	<head>
		<title>Id</title>
	</head>
<body>
<P>
<H2>Id</H2>
<spring:bind path="id">
  <FONT color="red">
    <B><c:out value="${status.errorMessage}"/></B>
  </FONT>
</spring:bind>
<form method="POST">
  <B>Id: </B>
  <spring:bind path="id">
    <FONT color="red">
      <B><c:out value="${status.errorMessage}"/></B>
    </FONT>
    <BR><input type="text" maxlength="10" size="10" name="id" value="<c:out value="${status.value}"/>" />
  </spring:bind>
  <P>
  <input type = "submit" value="Submit"/>
</FORM>
<P>
<BR>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
