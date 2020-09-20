package org.cch.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class BaseBean {
	
	private String name;
	
	@Autowired
	private BaseBean2 bean;
	
	public BaseBean() {
		System.out.println("BaseBean()");
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("BaseBean::setName()");
	}
	public void setBean(BaseBean2 bean) {
		this.bean = bean;
		System.out.println("BaseBean::setBean()");
	}
	

}
