//package com.example.springsecuritybatchjpa.config;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatasourceConfig {
//    @Bean
//    public DataSource datasource() {
//        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://0.0.0.0:3307/swaproj?autoReconnect=true&useSSL=false")
//                .username("root")
//                .password("123456")
//                .build();
//    }
//}