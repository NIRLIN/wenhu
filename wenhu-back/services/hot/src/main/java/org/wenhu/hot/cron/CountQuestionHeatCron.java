package org.wenhu.hot.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.wenhu.hot.service.impl.HotServiceImpl;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/25
 */

@Configuration
@EnableScheduling
public class CountQuestionHeatCron {

    @Autowired
    private HotServiceImpl hotService;

    /**
     * 运行开始计算，每日执行一次，每日6点0 0 6 * * ?
     * 每分钟一次0 0/1 0/1 * *  ?
     */
    @Scheduled(cron = "0 0/1 0/1 * *  ?")
    private void countQuestionHeat() {
        hotService.countQuestionHeat();
    }

}
