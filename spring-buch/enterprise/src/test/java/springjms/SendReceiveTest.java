package springjms;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jms.xml" })
public class SendReceiveTest  {

	@Resource(name="jmsTemplateSend")
	private JmsTemplate jmsTemplateSend;

	@Resource(name="jmsTemplateReceive")
	private JmsTemplate jmsTemplateReceive;

	private static int received = 0;

	@Test
	public void testSendReceive() throws InterruptedException {
		Thread receiverThread = new Thread(new Runnable() {
			public void run() {
				while (received < 2) {
					String result = (String) jmsTemplateReceive
							.receiveAndConvert();
					if (result.equals("JMS Message")) {
						received++;
					}
				}
			}
		});
		receiverThread.start();
		Assert.assertEquals(0, received);
		jmsTemplateSend.send(new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("JMS Message");
			}

		});
		jmsTemplateSend.convertAndSend("JMS Message");
		receiverThread.join();
		Assert.assertEquals(2, received);
	}

}
