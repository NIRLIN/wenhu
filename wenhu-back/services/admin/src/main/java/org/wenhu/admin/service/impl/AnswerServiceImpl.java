package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.AnswerService;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.database.dao.AnswerDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerDao answerDao;


    @Override
    public HashMap<String, Object> listAnswer(Map<String, Object> objectMap) {
        //获取分页信息
        Integer page = (Integer) objectMap.get("page");
        Integer answer = (Integer) objectMap.get("answer");
        //构建条件构造器
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        //获取回答列表
        List<AnswerDO> listAnswer = listAnswerPage(page, queryWrapper);
        //统计回答总数
        Integer listAnswerCount = listAnswerCount(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listAnswer", listAnswer);
        hashMap.put("listAnswerCount", listAnswerCount);
        return hashMap;
    }


    @Override
    public HashMap<String, Object> listAnswerBySearch(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        Integer answer = (Integer) objectMap.get("answer");
        String answerId = (String) objectMap.get("answerId");
        String questionId = (String) objectMap.get("questionId");
        String userId = (String) objectMap.get("userId");
        String content = (String) objectMap.get("content");
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(answerId != null, "id", answerId)
                .like(questionId != null, "question_id", questionId)
                .like(userId != null, "user_id", userId)
                .like(content != null, "content", content);
        List<AnswerDO> listAnswer = listAnswerPage(page, queryWrapper);
        Integer listAnswerSearchCount = listAnswerCount(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listAnswer", listAnswer);
        hashMap.put("listAnswerSearchCount", listAnswerSearchCount);
        return hashMap;
    }

    public List<AnswerDO> listAnswerPage(Integer page, QueryWrapper<AnswerDO> queryWrapper) {
        Page<AnswerDO> doPage = new Page<>(page, 10);
        queryWrapper
                .orderByDesc("update_time");
        return answerDao.selectPage(doPage, queryWrapper).getRecords();
    }

    public Integer listAnswerCount(QueryWrapper<AnswerDO> queryWrapper) {
        return answerDao.selectCount(queryWrapper);
    }


    @Override
    public HashMap<String, Object> updateAnswer(AnswerDO answerDO) {
        answerDO.setUpdateTime(LocalDateTime.now());
        int i = answerDao.updateById(answerDO);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        if (i == 1) {
            hashMap.put("updateAnswerResult", true);
        } else {
            hashMap.put("updateAnswerResult", true);
        }
        return hashMap;
    }


}
