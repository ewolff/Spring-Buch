package webservice;

import java.util.List;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

import businessobjects.Einkaufswagen;
import businessobjects.EinkaufswagenInhalt;
import businessprocess.BestellungException;
import businessprocess.IBestellungBusinessProcess;

public class BestellenEndpoint extends AbstractJDomPayloadEndpoint implements
		InitializingBean {

	private IBestellungBusinessProcess bestellung;

	private Namespace namespace;

	private XPath kundeIdXPath;

	private XPath einkaufswagenInhaltXPath;

	public void setBestellung(IBestellungBusinessProcess bestellung) {
		this.bestellung = bestellung;
	}

	protected Element invokeInternal(Element requestElement) throws Exception {
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(requestElement, System.out);

		Einkaufswagen einkaufswagen = new Einkaufswagen();
		einkaufswagen.setId_Kunde(Integer.parseInt(kundeIdXPath
				.valueOf(requestElement)));
		List<Element> einkaufswagenInhaltElements = einkaufswagenInhaltXPath
				.selectNodes(requestElement);
		for (Element element : einkaufswagenInhaltElements) {
			int anzahl = Integer.parseInt(element.getChildTextNormalize(
					"anzahl", namespace));
			int id_ware = Integer.parseInt(element.getChildTextNormalize(
					"id-ware", namespace));
			einkaufswagen.add(new EinkaufswagenInhalt(id_ware, anzahl));
		}
		String ergebnis;
		bestellung.bestellen(einkaufswagen);
		ergebnis = "OK";
		Element response = new Element("BestellenResponse", namespace);
		Element ergebnisElement = new Element("ergebnis", namespace);
		ergebnisElement.setText(ergebnis);
		response.addContent(ergebnisElement);
		return response;
	}

	public void afterPropertiesSet() throws Exception {
		namespace = Namespace.getNamespace("tns",
				"http://www.spring-buch.de/ws");
		kundeIdXPath = XPath
				.newInstance("/tns:bestellenRequest/tns:einkaufswagen/tns:kunde-id/text()");
		kundeIdXPath.addNamespace(namespace);
		einkaufswagenInhaltXPath = XPath
				.newInstance("/tns:bestellenRequest/tns:einkaufswagen/tns:einkaufswagen-inhalt");
		einkaufswagenInhaltXPath.addNamespace(namespace);
	}

}
