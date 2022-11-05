package com.example.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
@ConfigurationPropertiesScan
public class TestApplication {



    public static void main(String[] args) {

        SpringApplication.run(TestApplication.class, args);


    }

}
