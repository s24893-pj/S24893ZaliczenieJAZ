package com.example.jazs24893nbp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class NbpConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
