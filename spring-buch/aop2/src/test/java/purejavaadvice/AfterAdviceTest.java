package purejavaadvice;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(value = "/afterpurejavaadvice.xml", inheritLocations = false)
public class AfterAdviceTest extends annotationsadvice.AfterAdviceTest {

}
