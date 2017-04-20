package springperformance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;

public class PerformanceMain {

	private IDoIt[] beans;

	private int iterations = 100000;

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public void setBeans(IDoIt[] beans) {
		this.beans = beans;
	}

	public void run() {
		for (int i = 0; i < beans.length; i++) {
			IDoIt currentBean = beans[i];
			int warmup;
			if (iterations < 1000) {
				warmup = iterations;
			} else {
				warmup = 1000;
			}
			for (int j = 0; j < warmup; j++) {
				currentBean.doIt();
			}
			StopWatch stopWatch = new StopWatch(currentBean.getName());
			stopWatch.start();
			for (int j = 0; j < iterations; j++) {
				currentBean.doIt();
			}
			stopWatch.stop();
			System.out.println(stopWatch.shortSummary());
		}
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"performance.xml");
		PerformanceMain performanceMain = (PerformanceMain) applicationContext
				.getBean("performanceMain");
		performanceMain.run();
		System.out.println();
		System.out.println();
		performanceMain.run();
	}

}
