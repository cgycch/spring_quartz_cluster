package org.cch.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseBean {
	
	private String name;
	
	@Autowired
	private BaseBean2 bean;
	
	public BaseBean() {
		System.out.println("BaseBean()");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("BaseBean::setName()");
	}
	public BaseBean2 getBean() {
		return bean;
	}
	public void setBean(BaseBean2 bean) {
		this.bean = bean;
		System.out.println("BaseBean::setBean()");
	}
	

}
