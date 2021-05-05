package org.wenhu.hot.service.impl;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DO.ClassifyDO;
import org.wenhu.common.pojo.DO.HotDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.database.dao.AnswerDao;
import org.wenhu.database.dao.ClassifyDao;
import org.wenhu.database.dao.HotDao;
import org.wenhu.database.dao.QuestionDao;
import org.wenhu.hot.service.HotService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    private ClassifyDao classifyDao;


    @Autowired
    private AnswerDao answerDao;

    @GlobalTransactional
    @Override
    public Set<String> listHotQuestion() {

        setRedisHotQuestion();
        // {"id":"123","question_id":"123","question_hot":"123"}
        return stringRedisTemplate.opsForZSet().reverseRangeByScore("HotQuestion", 0, 100000000, 0, 50);
    }

    @GlobalTransactional
    @Override
    public Result<List<HotDO>> listHotByClassify(Map<String, Object> objectMap) {
        String classifyName = (String) objectMap.get("classifyName");
        QueryWrapper<ClassifyDO> classifyQueryWrapper = new QueryWrapper<>();
        classifyQueryWrapper.like("classify_name", classifyName);
        ClassifyDO classifyDO = classifyDao.selectOne(classifyQueryWrapper);
        QueryWrapper<HotDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("question_heat")
                .eq("is_deleted", 0);
        String classifyId = classifyDO.getId();
        if (!"全站".equals(classifyName)) {
            queryWrapper.eq("classify_id", classifyId);
        }
        Page<HotDO> hotPage = new Page<>();
        hotPage.setSize(50);
        List<HotDO> hotDOList = hotDao.selectPage(hotPage, queryWrapper).getRecords();
        return Result.succeed(hotDOList);
    }

    private void setRedisHotQuestion() {
        stringRedisTemplate.opsForZSet().removeRangeByScore("HotQuestion", 0, 100000000);

        QueryWrapper<HotDO> queryWrapper = new QueryWrapper<>();
        List<HotDO> hotDOList = hotDao.selectList(queryWrapper);
        for (HotDO hotDO : hotDOList) {
            stringRedisTemplate.opsForZSet().add("HotQuestion", String.valueOf(new JSONObject(hotDO)), hotDO.getQuestionHeat());
        }
    }

    @GlobalTransactional
    @Override
    public void countQuestionHeat() {
        QueryWrapper<QuestionDO> questionQueryWrapper = new QueryWrapper<>();
        List<QuestionDO> questionDOList = questionDao.selectList(questionQueryWrapper);
        QueryWrapper<HotDO> hotQueryWrapper = new QueryWrapper<>();
        int delete = hotDao.delete(hotQueryWrapper);
        for (QuestionDO questionDO : questionDOList) {
            QueryWrapper<AnswerDO> answerQueryWrapper = new QueryWrapper<>();
            Integer hotCount = answerDao.selectCount(answerQueryWrapper);
            HotDO hotDO = new HotDO()
                    .setId(String.valueOf(SnowflakeUtils.genId()))
                    .setQuestionId(questionDO.getId())
                    .setClassifyId(questionDO.getClassifyId())
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
