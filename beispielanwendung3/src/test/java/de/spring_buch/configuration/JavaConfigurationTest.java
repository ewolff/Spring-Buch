package de.spring_buch.configuration;

import org.springframework.test.context.ContextConfiguration;

import de.spring_buch.businessprocess.BestellungTestMitSpring;

@ContextConfiguration(locations={"/javaconfiguration.xml"}, inheritLocations=false)                                 
public class JavaConfigurationTest extends BestellungTestMitSpring {

}
