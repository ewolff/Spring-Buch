package lazyinit;

public class LazyBean {
	
	public static boolean constructorCalled = false;

    public LazyBean() {
        constructorCalled=true;
    }

    public void doIt() {
    }

}
