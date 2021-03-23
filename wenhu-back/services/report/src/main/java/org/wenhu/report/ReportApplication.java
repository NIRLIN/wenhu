package org.wenhu.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wenhu.dao.QuestionDao;
import org.wenhu.dao.UserDao;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */
@SpringBootApplication
public class ReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }
}
