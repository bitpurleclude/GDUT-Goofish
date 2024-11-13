package org.gdutgoodfish.goodfish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class GdutGoodfishApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdutGoodfishApplication.class, args);
    }

}
