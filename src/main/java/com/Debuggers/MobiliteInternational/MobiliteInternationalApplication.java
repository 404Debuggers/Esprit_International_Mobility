package com.Debuggers.MobiliteInternational;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MobiliteInternationalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobiliteInternationalApplication.class, args);
	}

}
