package resourceloader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/resourceloader.xml")
public class ResourceLoaderTest {

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void testResourceLoader() throws Exception {
		Resource resource = resourceLoader.getResource("resourceloader.xml");
		Assert.assertEquals("resourceloader.xml", resource.getFilename());
		Assert.assertTrue(resource.exists());
		System.out.println("Inhalt");
		InputStream inputStream = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		while (reader.ready()) {
			System.out.println(reader.readLine());
		}
	}

}
