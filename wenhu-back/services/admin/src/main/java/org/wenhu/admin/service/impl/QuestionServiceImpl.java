package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.QuestionService;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.database.dao.QuestionDao;

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
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public HashMap<String, Object> listQuestion(Map<String, Object> objectMap) {
        //获取分页信息
        Integer page = (Integer) objectMap.get("page");
        QueryWrapper<QuestionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        List<QuestionDO> listQuestion = listQuestionByPage(page, queryWrapper);
        //重置条件
        queryWrapper = new QueryWrapper<>();
        Integer countQuestion = countQuestion(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listQuestion", listQuestion);
        hashMap.put("countQuestion", countQuestion);
        return hashMap;
    }


    private List<QuestionDO> listQuestionByPage(Integer page, QueryWrapper<QuestionDO> queryWrapper) {
        //分页查询
        Page<QuestionDO> doPage = new Page<>(page, 10);
        Page<QuestionDO> selectPage = questionDao.selectPage(doPage, queryWrapper);
        return selectPage.getRecords();
    }

    private Integer countQuestion(QueryWrapper<QuestionDO> queryWrapper) {
        //查询总数
        return questionDao.selectCount(queryWrapper);
    }


    @Override
    public HashMap<String, Object> saveQuestion(QuestionDO questionDO) {
        questionDO.setUpdateTime(LocalDateTime.now());
        int i = questionDao.updateById(questionDO);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        if (i == 1) {
            hashMap.put("saveQuestionResult", true);
        } else {
            hashMap.put("saveQuestionResult", false);
        }
        return hashMap;
    }

    @Override
    public HashMap<String, Object> listQuestionSearch(Map<String, Object> objectMap) {

        Integer page = (Integer) objectMap.get("page");
        String questionId = (String) objectMap.get("questionId");
        String userId = (String) objectMap.get("userId");
        String title = (String) objectMap.get("title");
        String description = (String) objectMap.get("description");
        QueryWrapper<QuestionDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time")
                .like(questionId != null, "id", questionId)
                .like(userId != null, "user_id", userId)
                .like(title != null, "title", title)
                .like(description != null, "description", description);
        //分页查询
        Page<QuestionDO> doPage = new Page<>(page, 10);
        List<QuestionDO> listSearchQuestion = questionDao.selectPage(doPage, queryWrapper).getRecords();
        //查询总页数
        Integer listSearchQuestionCount = questionDao.selectCount(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listSearchQuestion", listSearchQuestion);
        hashMap.put("listSearchQuestionCount", listSearchQuestionCount);
        return hashMap;
    }
}
