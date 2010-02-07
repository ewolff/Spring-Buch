package config;

import test.BestellungDAOTest;
import test.BestellungOhneSpringTest;
import test.BestellungTestMitSpring;
import test.BestellungTransaktionTestMitSpring;
import test.KundeDAOTest;
import test.WareDAOTest;
import base.SpringTestCase;
import junit.extensions.TestSetup;
import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class SpringBuchAllTest extends TestCase {

	static class SpringTestSuite extends TestSuite {

		@Override
		public void runTest(Test test, TestResult result) {
			SpringTestCase.setConfigLocation(configLocation);
			super.runTest(test, result);
		}

		private String configLocation;

		public SpringTestSuite(String name, String configLocation) {
			super(name+" "+configLocation);
			this.configLocation = configLocation;
		}

	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new JUnit4TestAdapter(BestellungOhneSpringTest.class));
		suite.addTest(suite("aop.xml"));
		suite.addTest(suite("jpa-beans.xml"));
		suite.addTest(suite("spring2-jdbc-beans.xml"));
		suite.addTest(suite("hibernate-beans.xml"));
		suite.addTest(suite("ibatis-beans.xml"));
		suite.addTest(suite("spring-jdbc-beans.xml"));
		suite.addTest(suite("jdbc-beans-properties.xml"));
		suite.addTest(suite("jdbc-beans-properties1.xml"));
		suite.addTest(suite("jdbc-beans-abstract.xml"));
		suite.addTest(suite("jdbc-beans-autowire.xml"));
		suite.addTest(suite("jdbc-beans-constructor.xml"));
		suite.addTest(suite("jdbc-beans-p.xml"));
		suite.addTest(suite("jdbc-beans-required.xml"));
		suite.addTest(suite("spring2-jdbc-beans.xml"));
		return suite;
	}
	
	
	public static Test suite(String configLocation) {
		TestSuite suite = new SpringTestSuite("All Tests", configLocation);
		suite.addTestSuite(BestellungTestMitSpring.class);
		suite.addTestSuite(BestellungTransaktionTestMitSpring.class);
		suite.addTestSuite(KundeDAOTest.class);
		suite.addTestSuite(WareDAOTest.class);
		suite.addTestSuite(BestellungDAOTest.class);
		return suite;
	}
}
