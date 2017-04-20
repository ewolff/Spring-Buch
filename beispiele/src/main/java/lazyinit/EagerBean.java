package lazyinit;

public class EagerBean {

	public static boolean constructorCalled = false;

	public EagerBean() {
		super();
		constructorCalled = true;
	}

	public void doIt() {
	}
}
