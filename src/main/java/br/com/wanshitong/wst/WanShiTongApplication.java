package br.com.wanshitong.wst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WanShiTongApplication {

	public static void main(String[] args) {
		SpringApplication.run(WanShiTongApplication.class, args);
	}

}
