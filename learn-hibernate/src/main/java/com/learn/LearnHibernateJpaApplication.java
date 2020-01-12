package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.learn")
public class LearnHibernateJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnHibernateJpaApplication.class, args);

		System.out.println("I am running fine");
	}
}
