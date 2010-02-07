package web.kunde;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.springframework.web.servlet.view.xslt.AbstractXsltView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import businessobjects.Kunde;

public class KundenListeXsltView extends AbstractXsltView {

    protected Source createXsltSource(Map model, String root,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element rootElement = doc.createElement(root);

        List kunden = (List) model.get("kundenListe");
        for (int i = 0; i < kunden.size(); i++) {
            Kunde kunde = (Kunde) kunden.get(i);
            Element kundeElement = doc.createElement("kunde");
            addElement(doc, kundeElement, "id", Integer.toString(kunde.getId()));
            addElement(doc, kundeElement, "vorname", kunde.getVorname());
            addElement(doc, kundeElement, "name", kunde.getName());
            rootElement.appendChild(kundeElement);
        }

        doc.appendChild(rootElement);

        return new DOMSource(doc);        
    }

    private void addElement(Document doc, Element parentElement,
            String elementName, String elementValue) {
        Element result = doc.createElement(elementName);
        result.appendChild(doc.createTextNode(elementValue));
        parentElement.appendChild(result);
    }

}
