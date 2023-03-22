package com.example.loginsystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfinguration {


    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
