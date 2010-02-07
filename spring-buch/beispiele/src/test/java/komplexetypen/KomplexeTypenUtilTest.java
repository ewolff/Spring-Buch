package komplexetypen;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class KomplexeTypenUtilTest extends
		AbstractDependencyInjectionSpringContextTests {

	public KomplexeTypenUtilTest() {
		super();
		setAutowireMode(AUTOWIRE_BY_NAME);
	}

	private EineBean eineBean;

	private EineBean eineAndereBean;

	public void setEineBean(EineBean eineBean) {
		this.eineBean = eineBean;
	}

	protected String[] getConfigLocations() {
		return new String[] { "komplexetypenutil.xml" };
	}

	public void testKomplexeTypen() {
		eineBean.out();
		eineAndereBean.out();
	}

	public void setEineAndereBean(EineBean eineAndereBean) {
		this.eineAndereBean = eineAndereBean;
	}
}
