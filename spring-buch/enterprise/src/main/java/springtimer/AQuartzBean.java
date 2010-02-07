package springtimer;

public class AQuartzBean {

	private static boolean called = false;

	public static boolean isCalled() {
		return called;
	}

	public void doIt() {
		System.out.println("AQuartzBean called");
		called = true;
		synchronized (AQuartzBean.class) {
			AQuartzBean.class.notifyAll();
		}
	}

}
