package config;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.transaction.annotation.Transactional;

import de.spring_buch.businessprocess.BestellungOhneSpringTest;
import de.spring_buch.businessprocess.BestellungTestMitSpring;
import de.spring_buch.dao.BestellungDAOTest;
import de.spring_buch.dao.KundeDAOTest;
import de.spring_buch.dao.WareDAOTest;


@RunWith(Suite.class)
@Transactional
@Suite.SuiteClasses( { BestellungDAOTest.class, BestellungOhneSpringTest.class,
		BestellungTestMitSpring.class, KundeDAOTest.class, WareDAOTest.class })
public class SpringBuchAllTest  {

}
