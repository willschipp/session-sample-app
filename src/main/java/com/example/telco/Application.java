package com.example.telco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJdbcHttpSession
public class Application {

    public static void main(String... args) throws Exception {
        SpringApplication.run(Application.class,args);
    }
}
