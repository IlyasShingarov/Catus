package com.ishingarov.catus;

import com.ishingarov.catus.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(RsaKeyProperties.class)
@EnableWebMvc
public class Catus {
    public static void main(String[] args) {
        SpringApplication.run(Catus.class, args);
    }

}
