package com.senacead.PI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class PIApplication {

    public static void main(String[] args) {
        SpringApplication.run(PIApplication.class, args);
    }

}
