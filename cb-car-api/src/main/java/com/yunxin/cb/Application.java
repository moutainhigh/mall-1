package com.yunxin.cb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;


@Controller
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.yunxin.cb")
//mapper接口类的包名 加上
@MapperScan("com.yunxin.cb.mall.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
