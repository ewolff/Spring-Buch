package remotetest;

public class SpringTest extends RemoteBase {

	protected String[] getConfigLocations() {
		return new String[] {"spring-client.xml"};
	}

}
