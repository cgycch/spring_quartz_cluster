package org.cch.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {
	
	@Bean("bb")
	public BaseBean3 getMyBean() {
		BaseBean3 bean  = new BaseBean3();
		bean.setName("my bean3 is form @Bean(\"bb\")");
		return bean;
	}

}
