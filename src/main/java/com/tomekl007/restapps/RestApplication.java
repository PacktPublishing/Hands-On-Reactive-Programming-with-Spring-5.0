package com.tomekl007.restapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
		//you can use
		// -Dspring.profiles.active=dev when starting app instead of hardcoding it
		System.setProperty(
				AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		SpringApplication.run(RestApplication.class, args);
	}

}
