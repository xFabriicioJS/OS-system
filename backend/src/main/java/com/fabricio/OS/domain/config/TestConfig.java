package com.fabricio.OS.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fabricio.OS.services.ServiceDB;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private ServiceDB serviceDB;

    @Bean
    public void instanciaDB(){
        this.serviceDB.instanciaDB();
    }
}
