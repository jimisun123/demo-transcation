package com.example.localmessagetranscation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LocalmessageTranscationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalmessageTranscationApplication.class, args);
    }

}
