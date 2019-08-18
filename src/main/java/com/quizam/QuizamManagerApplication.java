package com.quizam;

import com.github.mongobee.Mongobee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class QuizamManagerApplication {

    @Value("${spring.data.mongodb.host}")
    private String hostname;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.database}")
    private String database;

    public static void main(String[] args) {
        SpringApplication.run(QuizamManagerApplication.class, args);
    }

    @Bean
    public Mongobee mongobee(){
        Mongobee runner = new Mongobee("mongodb://"+hostname+":"+port+"/");
        runner.setDbName(database);
        runner.setChangeLogsScanPackage("com.quizam.mongobee");
        return runner;
    }
}
