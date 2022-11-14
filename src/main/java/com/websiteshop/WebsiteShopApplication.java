package com.websiteshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.websiteshop.config.StorageProperties;
import com.websiteshop.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WebsiteShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteShopApplication.class, args);
    }


}