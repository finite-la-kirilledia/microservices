package com.finite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FraudApp {

    public static void main(String[] args) {
        SpringApplication.run(FraudApp.class, args);
    }
}
