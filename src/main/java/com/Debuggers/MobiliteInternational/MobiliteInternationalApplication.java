package com.Debuggers.MobiliteInternational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude =  {SecurityAutoConfiguration.class})
public class MobiliteInternationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobiliteInternationalApplication.class, args);
	}

}
