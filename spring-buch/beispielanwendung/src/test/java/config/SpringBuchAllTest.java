package config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;

import test.BestellungTestMitSpring;

@RunWith(Suite.class)
@Suite.SuiteClasses( { SpringBuchAllTest.Aop.class, SpringBuchAllTest.P.class,
		SpringBuchAllTest.Constructor.class, SpringBuchAllTest.Jdbc.class,
		SpringBuchAllTest.Jpa.class, SpringBuchAllTest.Spring2.class,
		SpringBuchAllTest.Required.class, SpringBuchAllTest.Autowire.class,
		SpringBuchAllTest.Abstract.class, SpringBuchAllTest.Hibernate.class,
		SpringBuchAllTest.IBatis.class, SpringBuchAllTest.Properties.class,
		SpringBuchAllTest.Properties1.class, SpringBuchAllTest.Debug.class })
public class SpringBuchAllTest {

	@ContextConfiguration(value = "/aop.xml", inheritLocations = false)
	public static class Aop extends BestellungTestMitSpring {

	}

	@ContextConfiguration(value = "/jdbc-beans-debug.xml", inheritLocations = false)
	public static class Debug extends BestellungTestMitSpring {

	}

	
	@ContextConfiguration(value = "/jpa-beans.xml", inheritLocations = false)
	public static class Jpa extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/spring2-jdbc-beans.xml", inheritLocations = false)
	public static class Spring2 extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/hibernate-beans.xml", inheritLocations = false)
	public static class Hibernate extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/ibatis-beans.xml", inheritLocations = false)
	public static class IBatis extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/spring-jdbc-beans.xml", inheritLocations = false)
	public static class Jdbc extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/jdbc-beans-properties.xml", inheritLocations = false)
	public static class Properties extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/jdbc-beans-properties1.xml", inheritLocations = false)
	public static class Properties1 extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/jdbc-beans-abstract.xml", inheritLocations = false)
	public static class Abstract extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/jdbc-beans-autowire.xml", inheritLocations = false)
	public static class Autowire extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/jdbc-beans-constructor.xml", inheritLocations = false)
	public static class Constructor extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/jdbc-beans-p.xml", inheritLocations = false)
	public static class P extends BestellungTestMitSpring {
	}

	@ContextConfiguration(value = "/jdbc-beans-required.xml", inheritLocations = false)
	public static class Required extends BestellungTestMitSpring {
	}

}
