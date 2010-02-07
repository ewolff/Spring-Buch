package introductions;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class IntroductionXMLTest extends
		AbstractDependencyInjectionSpringContextTests {

	private Bean bean;

	public void setBean(Bean bean) {
		this.bean = bean;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "introductions-xml.xml" };
	}

	public void testIntroduction() {
		for (int i = 0; i < 10; i++) {
			bean.doIt();
		}
		assertEquals(10, ((CallCounter) bean).getCounter());
	}
}
