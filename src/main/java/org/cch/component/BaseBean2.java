package org.cch.component;

import org.springframework.stereotype.Component;

@Component
public class BaseBean2 {
	
	private String name;
	
	public BaseBean2() {
		System.out.println("BaseBean2()");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null){
			name = "hello";
		}
		this.name = name;
		System.out.println("BaseBean2::setName()");
	}
	
	

}
