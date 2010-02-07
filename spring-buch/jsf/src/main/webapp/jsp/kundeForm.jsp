<%@include file="includes.jsp"%>

<head>
<title>Kunde erzeugen</title>
</head>

<body bgcolor="white">

<h2>Kunde</h2>

<f:view>
	<h:form id="kundeCreate">

		<p /><b> Vorname : </b><br />
		<h:message styleClass="validationMessage" for="vorname" /> <h:inputText
			id="vorname" value="#{kundecreate.kunde.vorname}" required="true" />
		<br />
		<p /><b>Name : </b><br />
		<h:message styleClass="validationMessage" for="name" /> <h:inputText
			id="name" value="#{kundecreate.kunde.name}" required="true" /> <br />
		<p /><b>Kontostand :</b><br />
		<h:message styleClass="validationMessage" for="kontostand" /> <h:inputText
			id="kontostand" value="#{kundecreate.kunde.kontostand}"
			validator="#{ kundecreate.validateKontostand}" required="true">
			<f:convertNumber pattern="#####.##" />
		</h:inputText> <br />
		<h:commandButton id="submit" action="#{kundecreate.submit}"
			value="Submit" />
	</h:form>
	<br />
</f:view>
<%@ include file="footer.jsp"%>