package springjmx;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/jmx-client.xml" })
public class JmxClient {

	@Resource(name = "counterClient")
	private IJmxCounter jmxCounter;

	@Test
	public void testMemoryMXBean() {
		Assert.assertTrue(jmxCounter.getCount() >= 0);
	}

}
