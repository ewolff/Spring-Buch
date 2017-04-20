package localtest;

import org.springframework.test.context.ContextConfiguration;

import remotetest.RemoteBase;

@ContextConfiguration( { "/rmi-server.xml", "/rmi-client.xml" })
public class RMITest extends RemoteBase {

}
