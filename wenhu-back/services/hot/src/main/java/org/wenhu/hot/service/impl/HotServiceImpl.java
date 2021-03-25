package org.wenhu.hot.service.impl;


import cn.hutool.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.HotDO;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.hot.service.HotService;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/2
 */

@Service
public class HotServiceImpl implements HotService<HotDO> {

    private final StringRedisTemplate stringRedisTemplate;

    public HotServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public Set<String> listHotQuestion() {
        saveHotQuestion();
        // {"id":"123","question_id":"123","question_hot":"123"}
        return stringRedisTemplate.opsForZSet().reverseRangeByScore("HotQuestion", 0, 100000, 0, 50);
    }

    void saveHotQuestion() {
        //如果数据少于10就随机添加数据
        int hotQuestion = Math.toIntExact(stringRedisTemplate.opsForZSet().count("HotQuestion", 0, 100000));
        if (hotQuestion < 10) {
            int heat = new Random().nextInt(100);
            JSONObject jsonObject = new JSONObject();
            jsonObject.putOpt("id", SnowflakeUtils.genId());
            jsonObject.putOpt("question_id", SnowflakeUtils.genId());
            jsonObject.putOpt("question_heat", heat);
            jsonObject.putOpt("question_title", "我是随机问题" + heat);
            jsonObject.putOpt("question_description", "我是随机问题描述" + heat);
            jsonObject.putOpt("create_time", LocalDateTime.now());
            jsonObject.putOpt("update_time", LocalDateTime.now());
            jsonObject.putOpt("is_deleted", 0);

//            String hotQuestionValue="\",\"question_id\":\"2\",\"question_hot\":\""+ hot+"\"}";
            stringRedisTemplate.opsForZSet().add("HotQuestion", String.valueOf(jsonObject), heat);
        }

    }


}
