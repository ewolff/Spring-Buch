package remotetest;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.transform.JDOMSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.xml.transform.StringResult;

public class Bestellen {

	private WebServiceTemplate webServiceTemplate;
	private Transformer transformer;

	@Before
	public void setUp() throws TransformerConfigurationException,
			TransformerFactoryConfigurationError {
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		webServiceTemplate = new WebServiceTemplate();
	}

	public String bestellen(int anzahl, int idWare) throws TransformerException {
		Source message = bestellenNachricht(anzahl, idWare);
		transformer.transform(message, new StreamResult(System.out));
		StringResult stringResult = new StringResult();
		webServiceTemplate.sendSourceAndReceiveToResult(
				"http://localhost:8080/webservice-annotations/services", message,
				stringResult);
		return stringResult.toString();
	}

	private static Source bestellenNachricht(int anzahl, int idWare) {
		Namespace namespace = Namespace.getNamespace("tns",
				"http://www.spring-buch.de/ws");
		Element message = new Element("bestellenRequest", namespace);
		Element einkaufswagen = new Element("einkaufswagen", namespace);
		message.addContent(einkaufswagen);
		einkaufswagen.addContent(new Element("kunde-id", namespace)
				.setText("95"));
		Element einkaufswagenInhalt = new Element("einkaufswagen-inhalt",
				namespace);
		einkaufswagenInhalt.addContent(new Element("anzahl", namespace)
				.setText(Integer.toString(anzahl)));
		einkaufswagenInhalt.addContent(new Element("id-ware", namespace)
				.setText(Integer.toString(idWare)));
		einkaufswagen.addContent(einkaufswagenInhalt);
		return new JDOMSource(message);
	}

	@Test
	public void erfolgreicheBestellung() throws Exception {
		String result = bestellen(1, 82);
		Assert.assertTrue(result.contains("BestellenResponse"));
	}

	@Test(expected = SoapFaultClientException.class)
	public void wareNichtVorhanden() throws Exception {
		bestellen(1, 21);
	}

}
