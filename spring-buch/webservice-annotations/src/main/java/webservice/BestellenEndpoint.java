package webservice;

import javax.xml.transform.Source;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.transform.JDOMSource;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.w3c.dom.NodeList;

import businessobjects.Einkaufswagen;
import businessobjects.EinkaufswagenInhalt;
import businessprocess.BestellungException;
import businessprocess.IBestellungBusinessProcess;

@Endpoint
public class BestellenEndpoint {

	private Namespace namespace = Namespace.getNamespace("tns",
			"http://www.spring-buch.de/ws");

	private IBestellungBusinessProcess bestellung;

	public void setBestellung(IBestellungBusinessProcess bestellung) {
		this.bestellung = bestellung;
	}

	@PayloadRoot(localPart = "bestellenRequest", namespace = "http://www.spring-buch.de/ws")
	public Source bestellen(
			@XPathParam("/tns:bestellenRequest/tns:einkaufswagen/tns:kunde-id/text()")
			Double id_Kunde,
			@XPathParam("/tns:bestellenRequest/tns:einkaufswagen/tns:einkaufswagen-inhalt")
			NodeList inhalt) throws BestellungException {
		Einkaufswagen einkaufswagen = new Einkaufswagen();
		einkaufswagen.setId_Kunde(id_Kunde.intValue());

		for (int i = 0; i < inhalt.getLength(); i++) {
			org.w3c.dom.Element element = (org.w3c.dom.Element) inhalt.item(i);
			int anzahl = Integer.parseInt(element.getElementsByTagNameNS(
					"http://www.spring-buch.de/ws", "anzahl").item(0)
					.getTextContent());
			int id_ware = Integer.parseInt(element.getElementsByTagNameNS(
					"http://www.spring-buch.de/ws", "id-ware").item(0)
					.getTextContent());
			einkaufswagen.add(new EinkaufswagenInhalt(id_ware, anzahl));
		}
		String ergebnis;
		bestellung.bestellen(einkaufswagen);
		ergebnis = "OK";
		Element response = new Element("BestellenResponse", namespace);
		Element ergebnisElement = new Element("ergebnis", namespace);
		ergebnisElement.setText(ergebnis);
		response.addContent(ergebnisElement);
		return new JDOMSource(response);
	}

}
