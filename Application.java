package com.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * This is the main program to start this application as Spring Boot.
 * It loads all necessary Spring libraries to publish the RESTful web service
 * It runs on 8080 port number as it has in-built Tomcat from Spring Boot.
 */


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
