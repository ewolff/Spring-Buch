package beanname;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean implements BeanNameAware {

	private String beanName;

	public void setBeanName(String name) {
	this.beanName=name;
	}

	public String getBeanName() {
		return beanName;
	}



}
