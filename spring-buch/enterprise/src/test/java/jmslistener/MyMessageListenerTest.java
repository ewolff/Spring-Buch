package jmslistener;

import javax.annotation.Resource;

import jmslistener.MyMessageListener;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jms.xml" })
public class MyMessageListenerTest {

	@Resource(name = "myMessageListener")
	private MyMessageListener myMessageListener;

	@Resource(name = "jmsTemplateMessageListener")
	private JmsTemplate jmsTemplateMessageListener;

	@Test
	public void testAdaptor() throws InterruptedException {
		Assert.assertFalse(myMessageListener.isCalled());
		jmsTemplateMessageListener.convertAndSend("Eine Message");
		synchronized (MyMessageListener.class) {
			MyMessageListener.class.wait(5000);
		}
		Assert.assertTrue(myMessageListener.isCalled());
	}

}
