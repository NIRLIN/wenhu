package org.wenhu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wenhu.dao.QuestionDao;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
