package org.cch;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class InAppBeanFactory implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		System.out.println("InAppBeanFactory::setApplicationContext()");
		InAppBeanFactory.applicationContext = context;
	}

	public static <T> T getBean(Class<T> cls) {
		return (T) applicationContext.getBean(cls);
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

}
