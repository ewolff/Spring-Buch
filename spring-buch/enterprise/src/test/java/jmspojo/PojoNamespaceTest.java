package jmspojo;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "/jms-namespace.xml" }, inheritLocations=false)
public class PojoNamespaceTest extends PojoTest {

}
