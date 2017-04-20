package events;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerDemo implements ApplicationListener {

	private boolean called;

	private MyEvent myEvent;

	public boolean isCalled() {
		return called;
	}

	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println(event);
		if (event instanceof MyEvent) {
			myEvent = (MyEvent) event;
		}
		called = true;
	}

	public MyEvent getMyEvent() {
		return myEvent;
	}

}
