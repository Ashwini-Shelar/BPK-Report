package com.ln.bpk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/*@SpringBootApplication
@EnableScheduling
@EnableAsync
@ComponentScan({ "com.ln.bpk.processor", "com.ln.bpk.service",  "com.ln.bpk.utils" })*/
@SpringBootApplication(scanBasePackages = { "com.ln.bpk" })
public class BpkReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpkReportServiceApplication.class, args);
	}

}
