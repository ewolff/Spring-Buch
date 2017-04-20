package introductions;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(value = "/introductions-xml.xml", inheritLocations = false)
public class IntroductionXMLTest extends IntroductionsTest {

}
