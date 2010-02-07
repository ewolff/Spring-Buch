package events;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/events.xml" })
public class ApplicationListenerTest {

	@Autowired
	private ApplicationListenerDemo applicationListenerDemo;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ApplicationSenderDemo applicationSenderDemo;

	@Test
	public void testEvent() {
		applicationContext.publishEvent(new MyEvent(applicationContext));
		Assert.assertTrue(applicationListenerDemo.isCalled());
	}

	@Test
	public void testSource() {
		applicationSenderDemo.sendEvent();
		Assert.assertSame(applicationSenderDemo, applicationListenerDemo
				.getMyEvent().getSource());
	}
}
