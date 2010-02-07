package lazyinit;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class LazyInitMain extends AbstractDependencyInjectionSpringContextTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "lazyinit.xml" };
	}

	public void testLazy() {
		assertTrue(EagerBean.constructorCalled);
		EagerBean eineEagerBean = (EagerBean) applicationContext
				.getBean("eagerBean");
		eineEagerBean.doIt();

		assertFalse(LazyBean.constructorCalled);
		LazyBean eineLazyBean = (LazyBean) applicationContext
				.getBean("lazyBean");
		assertTrue(LazyBean.constructorCalled);
		eineLazyBean.doIt();
	}

}
