package org.greece.mythology.eros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ErosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErosApplication.class, args);
    }

}
