package org.wenhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@SpringBootApplication
@EnableDiscoveryClient
public class PeopleApplication {
    public static void main(String[] args) {
        SpringApplication.run(PeopleApplication.class, args);
    }
}
