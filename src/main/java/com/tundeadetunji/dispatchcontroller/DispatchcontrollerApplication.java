package com.tundeadetunji.dispatchcontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DispatchcontrollerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchcontrollerApplication.class, args);
		log.info("* Application started - http://localhost:8080/api/");
	}

}
