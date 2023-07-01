package com.neu.airquality;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neu.airquality.mapper.**")
public class AirQualityApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirQualityApplication.class, args);
    }

}
