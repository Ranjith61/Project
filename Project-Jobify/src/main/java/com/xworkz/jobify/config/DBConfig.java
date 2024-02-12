package com.xworkz.jobify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class DBConfig {
	
	@Bean
	public LocalEntityManagerFactoryBean getEntityManager() {
		LocalEntityManagerFactoryBean managerFactory = new LocalEntityManagerFactoryBean();
		managerFactory.setPersistenceUnitName("jobify-connection");
		return managerFactory;
	}

}
