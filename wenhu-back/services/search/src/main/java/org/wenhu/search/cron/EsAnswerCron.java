package org.wenhu.search.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.wenhu.search.service.impl.EsAnswerServiceImpl;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/25
 */

@Configuration
@EnableScheduling
public class EsAnswerCron {

    @Autowired
    private EsAnswerServiceImpl esAnswerService;

    /**
     * 运行开始计算，每日执行一次，每日6点0 0 6 * * ?
     * 每分钟一次0 0/1 0/1 * *  ?
     */
    @Scheduled(cron = "0 0/1 0/1 * *  ?")
    private void createEsAnswer() {
        System.err.println("createEsAnswer " + LocalDateTime.now());
        esAnswerService.deleteAllEsAnswer();
        esAnswerService.saveAllEsAnswer();
    }

    /**
     * 检查最近20秒添加的回答
     */
    @Scheduled(fixedRate = 20000)
    private void checkRecentEsAnswer() {
        System.err.println("checkRecentEsAnswer " + LocalDateTime.now());
        esAnswerService.checkRecentEsAnswer();
    }


}
