package com.example.Services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class GameConfig {

    private static final long RANDOM_SEED = 999999L;

    @Bean
    public Random random(){
        return new Random(RANDOM_SEED);
    }
}