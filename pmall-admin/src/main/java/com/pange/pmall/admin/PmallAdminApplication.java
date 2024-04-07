package com.pange.pmall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pange.pmall")
public class PmallAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(PmallAdminApplication.class,args);
    }
}