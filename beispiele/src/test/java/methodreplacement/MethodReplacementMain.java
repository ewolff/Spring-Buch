package methodreplacement;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/methodreplacement.xml")
public class MethodReplacementMain {

	@Autowired
	private Bean originalbean;

	@Autowired
	private Bean replaced;

	@Test
	public void testReplace() {
		Assert.assertEquals(0, Bean.doItCount);
		originalbean.doIt();
		Assert.assertEquals(1, Bean.doItCount);
		replaced.doIt();
		Assert.assertEquals(1, Bean.doItCount);
	}
}
