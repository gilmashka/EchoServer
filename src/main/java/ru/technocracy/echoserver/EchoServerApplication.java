package ru.technocracy.echoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EchoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoServerApplication.class, args);
    }

}
