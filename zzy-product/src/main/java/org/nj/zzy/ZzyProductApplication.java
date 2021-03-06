package org.nj.zzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "org.nj.zzy")
public class ZzyProductApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication. run(ZzyProductApplication.class, args);
    }
}
