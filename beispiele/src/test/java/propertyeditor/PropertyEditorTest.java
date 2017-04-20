package propertyeditor;

import java.text.SimpleDateFormat;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/propertyeditor.xml")
public class PropertyEditorTest {

	@Autowired
	DateExample dateExample;

	@Test
	public void testDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Assert.assertEquals("02.01.2001", dateFormat.format(dateExample
				.getDate()));
	}

}
