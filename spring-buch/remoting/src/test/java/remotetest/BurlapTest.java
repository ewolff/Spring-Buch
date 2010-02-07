package remotetest;

import org.springframework.remoting.RemoteAccessException;

public class BurlapTest extends RemoteBase {

	protected String[] getConfigLocations() {
		return new String[] {"burlap-client.xml"};
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(BurlapTest.class);
//		throw new RemoteAccessException("Hi!");
	}

}
