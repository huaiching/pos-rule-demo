package com.mli.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class FlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowApplication.class, args);
	}

}
