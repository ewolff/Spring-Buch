package lazyinit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/lazyinit.xml")
public class LazyInitMain {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testLazy() {
		Assert.assertTrue(EagerBean.constructorCalled);
		EagerBean eineEagerBean = (EagerBean) applicationContext
				.getBean("eagerBean");
		eineEagerBean.doIt();

		Assert.assertFalse(LazyBean.constructorCalled);
		LazyBean eineLazyBean = (LazyBean) applicationContext
				.getBean("lazyBean");
		Assert.assertTrue(LazyBean.constructorCalled);
		eineLazyBean.doIt();
	}

}
