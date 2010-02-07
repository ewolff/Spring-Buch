package jmsexporter;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jms.xml" })
public class KundeDAOTest {

	@Autowired
	private IKundeDAO kundeDAO;

	@Test
	public void testConversion() {
		Kunde kunde = kundeDAO.getById(42);
		Assert.assertEquals("wolff", kunde.getName());
	}

}
