package springtimer;

import junit.framework.Assert;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

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
