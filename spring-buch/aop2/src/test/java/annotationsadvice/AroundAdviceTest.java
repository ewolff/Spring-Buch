package annotationsadvice;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import annotationadvice.LogInsert;
import businessobjects.Einkaufswagen;
import businessprocess.BestellungBusinessProcess;
import businessprocess.BestellungException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/aroundannotationadvice.xml")
public class AroundAdviceTest {

	@Autowired
	private BestellungBusinessProcess bestellung;

	@Test(expected = BestellungException.class)
	public void testLogging() throws BestellungException {
		LogInsert.reset();
		Assert.assertFalse(LogInsert.isCalled());
		bestellung.bestellen(new Einkaufswagen());
		try {
			bestellung.bestellen(null);
		} finally {
			Assert.assertTrue(LogInsert.isCalled());
		}

	}

}
