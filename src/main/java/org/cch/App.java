package org.cch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext springContext = 
				new ClassPathXmlApplicationContext(new String[]{"classpath:spring-app.xml","classpath:spring-quartz.xml"});
		System.out.println("Main方法执行Quartz");
    }
}
