package annotationsadvice;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import annotationadvice.LogInsert;
import businessobjects.Einkaufswagen;
import businessprocess.BestellungBusinessProcess;
import businessprocess.BestellungException;

public class AfterAdviceTest extends
		AbstractDependencyInjectionSpringContextTests {

	private BestellungBusinessProcess bestellung;

	public void setBestellung(BestellungBusinessProcess bestellung) {
		this.bestellung = bestellung;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "afterannotationadvice.xml" };

	}

	public void testLogging() throws BestellungException {		
		LogInsert.reset();
		assertFalse(LogInsert.isCalled());
		bestellung.bestellen(new Einkaufswagen());
		try {
			bestellung.bestellen(null);
			fail("Exception erwartet");
		} catch (BestellungException e) {
		}
		assertTrue(LogInsert.isCalled());
	}

}
