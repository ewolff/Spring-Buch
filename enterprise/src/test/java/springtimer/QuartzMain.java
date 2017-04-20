package springtimer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Assert;

public class QuartzMain {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("quartz.xml");
		synchronized (AQuartzBean.class) {
			AQuartzBean.class.wait(3000);			
		}
		Assert.assertTrue(AQuartzBean.isCalled());
		Thread.sleep(60000);
		applicationContext.stop();
		applicationContext.close();
	}

}
