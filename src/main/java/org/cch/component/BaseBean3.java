package org.cch.component;

public class BaseBean3 {
	
	private String name;
	
	public BaseBean3() {
		super();
		System.out.println("BaseBean3()");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("BaseBean3::setName()");
	}
	

}
