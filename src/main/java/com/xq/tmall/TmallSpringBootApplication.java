package com.xq.tmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.xq.tmall.dao")
public class TmallSpringBootApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TmallSpringBootApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(TmallSpringBootApplication.class,args);
    }
}
