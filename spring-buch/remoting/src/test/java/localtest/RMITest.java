package localtest;

import org.springframework.test.context.ContextConfiguration;

import remotetest.RemoteBase;

@ContextConfiguration( { "/rmi-client.xml", "/rmi-server.xml" })
public class RMITest extends RemoteBase {

}
