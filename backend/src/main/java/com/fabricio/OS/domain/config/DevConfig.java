package com.fabricio.OS.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.fabricio.OS.services.ServiceDB;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private ServiceDB serviceDB;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Bean
    public boolean instanciaDB(){

        if(this.ddl.equals("create")){
            this.serviceDB.instanciaDB();
        }
        return false;
    }
}
