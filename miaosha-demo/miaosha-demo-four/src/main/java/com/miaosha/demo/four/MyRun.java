package com.miaosha.demo.four;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.miaosha.common","com.miaosha.demo.four"})
@MapperScan(basePackages = {"com.miaosha.common.dao"})
public class MyRun {

    public static void main(String[] args) {
        SpringApplication.run(MyRun.class,args);
    }

}
