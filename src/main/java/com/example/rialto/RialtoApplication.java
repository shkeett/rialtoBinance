package com.example.rialto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RialtoApplication {

    public static void main(String[] args)  {
        SpringApplication.run(RialtoApplication.class, args);


    }
}
