package com.cmlteam.codevscovid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CodeVsCovid19Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CodeVsCovid19Application.class, args);

        context.getBean(PopulateService.class).populateTables();
    }

}
