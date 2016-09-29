package com.jbali.pos;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.jbali"})
public class PointOfSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointOfSalesApplication.class, args);
	    }
	}

