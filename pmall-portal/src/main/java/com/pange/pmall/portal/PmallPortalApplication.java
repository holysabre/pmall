package com.pange.pmall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pange.pmall")
public class PmallPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PmallPortalApplication.class,args);
    }
}