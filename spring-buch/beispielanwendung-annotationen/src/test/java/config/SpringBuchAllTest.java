package config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.transaction.annotation.Transactional;

import base.BestellungDAOTest;
import base.BestellungOhneSpringTest;
import base.BestellungTestMitSpring;
import base.KundeDAOTest;
import base.WareDAOTest;

@RunWith(Suite.class)
@Transactional
@Suite.SuiteClasses( { BestellungDAOTest.class, BestellungOhneSpringTest.class,
		BestellungTestMitSpring.class, KundeDAOTest.class, WareDAOTest.class })
public class SpringBuchAllTest  {

}
