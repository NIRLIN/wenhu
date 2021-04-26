package org.wenhu.hot.service.impl;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.common.pojo.DO.HotDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.database.dao.AnswerArticleDao;
import org.wenhu.database.dao.HotDao;
import org.wenhu.database.dao.QuestionDao;
import org.wenhu.hot.service.HotService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/2
 */

@Service
public class HotServiceImpl implements HotService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private HotDao hotDao;


    @Autowired
    private QuestionDao questionDao;


    @Autowired
    private AnswerArticleDao answerArticleDao;


    @Override
    public Set<String> listHotQuestion() {

        setHotQuestion();
        // {"id":"123","question_id":"123","question_hot":"123"}
        return stringRedisTemplate.opsForZSet().reverseRangeByScore("HotQuestion", 0, 100000000, 0, 50);
    }

    private void setHotQuestion() {
        stringRedisTemplate.opsForZSet().removeRangeByScore("HotQuestion", 0, 100000000);

        QueryWrapper<HotDO> queryWrapper = new QueryWrapper<>();
        List<HotDO> hotDOS = hotDao.selectList(queryWrapper);
        for (HotDO hotDO : hotDOS) {
            JSONObject jsonObject = new JSONObject(hotDO);
            stringRedisTemplate.opsForZSet().add("HotQuestion", String.valueOf(jsonObject), hotDO.getQuestionHeat());
        }
    }


    @Override
    public void countQuestionHeat() {
        QueryWrapper<QuestionDO> questionDOQueryWrapper = new QueryWrapper<>();
        List<QuestionDO> questionDOList = questionDao.selectList(questionDOQueryWrapper);
        QueryWrapper<HotDO> hotDOQueryWrapper = new QueryWrapper<>();
        int delete = hotDao.delete(hotDOQueryWrapper);
        for (QuestionDO questionDO : questionDOList) {
            QueryWrapper<AnswerArticleDO> answerArticleDOQueryWrapper = new QueryWrapper<>();
            Integer hotCount = answerArticleDao.selectCount(answerArticleDOQueryWrapper);
            HotDO hotDO = new HotDO()
                    .setId(String.valueOf(SnowflakeUtils.genId()))
                    .setQuestionId(questionDO.getId())
                    .setQuestionTitle(questionDO.getTitle())
                    .setQuestionDescription(questionDO.getDescription())
                    .setQuestionHeat(hotCount * 100)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now())
                    .setIsDeleted(0);
            hotDao.insert(hotDO);
        }

    }
}
