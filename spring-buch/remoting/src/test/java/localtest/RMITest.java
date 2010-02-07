package localtest;

import remotetest.RemoteBase;


public class RMITest extends RemoteBase {

	protected String[] getConfigLocations() {
		return new String[] { "rmi-client.xml", "rmi-server.xml" };
	}

}
