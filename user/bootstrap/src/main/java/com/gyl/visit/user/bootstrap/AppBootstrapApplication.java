package com.gyl.visit.user.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.gyl.visit.*")
public class AppBootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppBootstrapApplication.class, args);
    }
}

