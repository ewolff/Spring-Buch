package jmslistener;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "/jms-namespace.xml" }, inheritLocations = false)
public class MyMessageListenerNamespaceTest extends MyMessageListenerTest {

}
