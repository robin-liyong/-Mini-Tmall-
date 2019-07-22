package com.xq.tmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.xq.tmall.dao")
public class TmallSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallSpringBootApplication.class,args);
    }
}
