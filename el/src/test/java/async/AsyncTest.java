package async;

import java.util.concurrent.ExecutionException;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/async.xml")
public class AsyncTest {

	@Autowired
	AsyncReceiver asyncReceiver;

	@Test
	public void testAsync() throws Exception {
		asyncReceiver.test(Thread.currentThread());
		Assert.assertEquals(null, asyncReceiver.testReturn(Thread
				.currentThread()));
		Assert.assertEquals("something", asyncReceiver.testReturnFuture(
				Thread.currentThread()).get());
	}

}
