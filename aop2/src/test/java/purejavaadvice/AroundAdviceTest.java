package purejavaadvice;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(value = "/aroundpurejavaadvice.xml", inheritLocations = false)
public class AroundAdviceTest extends annotationsadvice.AroundAdviceTest {

}
