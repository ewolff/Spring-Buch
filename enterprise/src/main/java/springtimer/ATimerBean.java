package springtimer;

public class ATimerBean {
	private static boolean called = false;

	public static boolean isCalled() {
		return called;
	}

	public void doIt() {
		called = true;
	}

}
