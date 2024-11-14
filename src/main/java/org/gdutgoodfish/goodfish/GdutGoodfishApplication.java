package org.gdutgoodfish.goodfish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("org.gdutgoodfish.goodfish.mapper")
public class GdutGoodfishApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdutGoodfishApplication.class, args);
    }

}
