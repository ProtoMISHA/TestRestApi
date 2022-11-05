package com.example.test.configuration;


import com.example.test.configuration.properties.DatabaseProperties;
import com.example.test.configuration.properties.HibernateProperties;
import com.example.test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataBaseConfiguration {

    public static final String DATABASE_PROPETIES = "databaseProperties";

    public static final String HIBERNATE_PROPERTIES = "hibernateProperties";

    @Autowired
    @Qualifier(value = DATABASE_PROPETIES)
    DatabaseProperties databaseProperties;

    @Autowired
    @Qualifier(value = HIBERNATE_PROPERTIES)
    HibernateProperties hibernateProperties;




    @Bean
    public DataSource dataSource(){

        DataSource dataSource = DataSourceBuilder.create()
                                .driverClassName(databaseProperties.getClassDriver())
                                .password(databaseProperties.getPassword())
                                .username(databaseProperties.getLogin())
                                .url(databaseProperties.getUrl())
                                .build();

        return dataSource;
    }


    @Bean
    public Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",  hibernateProperties.getDialect());
        return properties;

    }




    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource());

        localSessionFactoryBean.setHibernateProperties(properties());

        localSessionFactoryBean.setAnnotatedClasses(Book.class);

        return localSessionFactoryBean;

    }


    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        HibernateTransactionManager hbr = new HibernateTransactionManager();
        hbr.setSessionFactory(localSessionFactoryBean().getObject());

        return hbr;

    }




}
