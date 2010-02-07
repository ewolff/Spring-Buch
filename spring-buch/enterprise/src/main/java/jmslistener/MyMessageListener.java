package jmslistener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class MyMessageListener implements
		SessionAwareMessageListener {

	private boolean called = false;

	public boolean isCalled() {
		return called;
	}

	public void onMessage(Message message, Session session) throws JMSException {
		called = true;
		synchronized (MyMessageListener.class) {
			MyMessageListener.class.notifyAll();
		}
	}

}
