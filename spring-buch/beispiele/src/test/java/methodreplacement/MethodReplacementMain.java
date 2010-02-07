package methodreplacement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class MethodReplacementMain extends
		AbstractDependencyInjectionSpringContextTests {

	public MethodReplacementMain() {
		super();
		setAutowireMode(AUTOWIRE_BY_NAME);
	}

	private Bean originalbean;

	private Bean replaced;

	public void setOriginalbean(Bean originalbean) {
		this.originalbean = originalbean;
	}

	public void setReplaced(Bean replaced) {
		this.replaced = replaced;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "methodreplacement.xml" };
	}

	public void testReplace() {
		assertEquals(0, Bean.doItCount);
		originalbean.doIt();
		assertEquals(1, Bean.doItCount);
		replaced.doIt();
		assertEquals(1, Bean.doItCount);
	}
}
