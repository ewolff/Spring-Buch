package jmspojo;

public class MessageHandlerPojo {

	private boolean called = false;

	public boolean isCalled() {
		return called;
	}

	public void handleJMSMessage(String message) {
		called = true;
		synchronized (MessageHandlerPojo.class) {
			MessageHandlerPojo.class.notifyAll();
		}
	}

}
