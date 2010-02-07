package async;

import java.util.concurrent.Future;

import junit.framework.Assert;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

public class AsyncReceiver {

	@Async
	public void test(Thread thread) {
		Assert.assertNotSame(Thread.currentThread(), thread);
	}

	@Async
	public String testReturn(Thread thread) {
		Assert.assertNotSame(Thread.currentThread(), thread);
		return "something";
	}

	@Async
	public Future<String> testReturnFuture(Thread thread) {
		Assert.assertNotSame(Thread.currentThread(), thread);
		return new AsyncResult<String>("something");
	}

}
