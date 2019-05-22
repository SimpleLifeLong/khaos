package org.greece.mythology.uranus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "org.greece.mythology.erebus")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.greece.mythology.uranus.repository")
@EntityScan(basePackages = "org.greece.mythology.tartarus.beans.entity")
public class UranusApplication {

    public static void main(String[] args) {
        SpringApplication.run(UranusApplication.class, args);
    }

}
