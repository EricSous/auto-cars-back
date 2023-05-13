package com.automix.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AutomixApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomixApplication.class, args);
    }

}
