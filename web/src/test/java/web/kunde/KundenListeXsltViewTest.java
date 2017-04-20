package web.kunde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import businessobjects.Kunde;

public class KundenListeXsltViewTest extends TestCase {

	public KundenListeXsltViewTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCreateXsltSourceMapStringHttpServletRequestHttpServletResponse()
			throws Exception {
		KundenListeXsltView view = new KundenListeXsltView();
		Map model;
		model = new HashMap();

		List kundenListe;
		kundenListe = new ArrayList<Kunde>();
		kundenListe.add(new Kunde("Eberhard", "Wolff", 42.0));
		model.put("kundenListe", kundenListe);
		view.locateSource(model);
	}

}
