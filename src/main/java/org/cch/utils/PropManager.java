package org.cch.utils;

import java.util.HashMap;
import java.util.Map;

public class PropManager {
	
	public static Map<String,String> props = new HashMap<>();
	
	public  static  String getProp(String key) {
		return props.get(key);
	}
	public  static  void setProp(String key,String value) {
		props.put(key, value);
	}
}
