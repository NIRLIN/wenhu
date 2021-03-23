package org.wenhu.hot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.wenhu.dao.QuestionDao;
import org.wenhu.dao.UserDao;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/22
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HotApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotApplication.class,args);
    }
}
