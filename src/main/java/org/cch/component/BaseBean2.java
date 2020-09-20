package org.cch.component;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class BaseBean2 {
	
	private String name;
	
	public BaseBean2() {
		System.out.println("BaseBean2()");
	}

	public void setName(String name) {
		if(name == null){
			name = "hello";
		}
		this.name = name;
		System.out.println("BaseBean2::setName()");
	}
	
	

}
