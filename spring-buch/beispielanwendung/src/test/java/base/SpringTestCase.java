package base;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class SpringTestCase extends AbstractTransactionalDataSourceSpringContextTests {

	private static boolean loggedSpringFile = false;

	private static String configLocation = "spring-jdbc-beans.xml";

	public static void setConfigLocation(String configLocation) {
		SpringTestCase.configLocation = configLocation;
	}

	public SpringTestCase() {
		super();
		setAutowireMode(AUTOWIRE_BY_NAME);
	}

	protected String[] getConfigLocations() {
		return new String[] { configLocation };
	}

}