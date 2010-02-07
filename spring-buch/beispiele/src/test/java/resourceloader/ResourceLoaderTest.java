package resourceloader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class ResourceLoaderTest extends
		AbstractDependencyInjectionSpringContextTests {

	private ResourceLoader resourceLoader;

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[]{"resourceloader.xml"};
	}

	public void testResourceLoader() throws Exception {
		Resource resource = resourceLoader.getResource("resourceloader.xml");
		assertEquals("resourceloader.xml", resource.getFilename());
		assertTrue(resource.exists());
		System.out.println("Inhalt");
		InputStream inputStream = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		while (reader.ready()) {
			System.out.println(reader.readLine());
		}
	}

}
