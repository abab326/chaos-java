package org.liushuxue.chaos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.liushuxue.chaos.mapper")
@SpringBootApplication
public class ChaosJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaosJavaApplication.class, args);
    }

}
