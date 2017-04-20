package eltestxml;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eltestxml.EineAndereBean;
import eltestxml.EineBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/xml-el.xml" })
public class XmlElTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private EineAndereBean eineAndereBean;
	@Autowired
	private EineBean eineBean;

	@BeforeClass
	public static void setProperty() {
		System.getProperties().put("country", "DE");
	}

	@Test
	public void testSpringBeanEl() {
		Assert.assertEquals(eineBean.getName(), eineAndereBean.getName());
		Assert.assertEquals("Johnson", eineAndereBean.getNochEinName());
		Assert.assertEquals(2, eineAndereBean.getZwei());
	}


}
