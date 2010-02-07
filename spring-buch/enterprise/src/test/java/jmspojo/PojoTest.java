package jmspojo;

import javax.annotation.Resource;

import jmspojo.MessageHandlerPojo;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jms.xml" })
public class PojoTest {

	@Autowired
	private MessageHandlerPojo messageHandler;

	@Resource(name = "jmsTemplatePojo")
	private JmsTemplate jmsTemplateMessageHandler;

	@Test
	public void testAdaptor() throws InterruptedException {
		Assert.assertFalse(messageHandler.isCalled());
		jmsTemplateMessageHandler.convertAndSend("Eine Message");
		synchronized (MessageHandlerPojo.class) {
			MessageHandlerPojo.class.wait(5000);
		}
		Assert.assertTrue(messageHandler.isCalled());
	}

}
