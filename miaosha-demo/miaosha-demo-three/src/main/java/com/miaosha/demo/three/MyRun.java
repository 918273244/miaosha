package com.miaosha.demo.three;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.miaosha.common","com.miaosha.demo.three"})
@MapperScan(basePackages = {"com.miaosha.common.dao"})
public class MyRun {

    public static void main(String[] args) {
        SpringApplication.run(MyRun.class,args);
    }

}
