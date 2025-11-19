package edu.ucsal.fiadopay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "paymentPool")
    public ExecutorService paymentPool() {
        return Executors.newFixedThreadPool(5);
    }

    @Bean(name = "webhookPool") 
    public ExecutorService webhookPool() {
        return Executors.newFixedThreadPool(3);
    }
}