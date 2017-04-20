package eltestannotation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/annotation-el.xml" })
public class AnnotationElTest {

	@Autowired
	private EineAndereBean eineAndereBean;

	@BeforeClass
	public static void setProperty() {
		System.getProperties().put("country", "DE");
	}

	@Test
	public void testSpringBeanEl() {
		Assert.assertEquals("DE", eineAndereBean.getCountry());
	}
	

	
}
