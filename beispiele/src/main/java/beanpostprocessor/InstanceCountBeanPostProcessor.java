package beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class InstanceCountBeanPostProcessor implements BeanPostProcessor {

    private int count;
    
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        count++;
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

}
