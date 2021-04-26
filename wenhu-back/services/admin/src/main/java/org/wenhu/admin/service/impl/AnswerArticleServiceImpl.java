package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.AnswerArticleService;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.database.dao.AnswerArticleDao;

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
public class AnswerArticleServiceImpl implements AnswerArticleService {
    @Autowired
    private AnswerArticleDao answerArticleDao;

    @Override
    public HashMap<String, Object> listAnswerArticle(Map<String, Object> objectMap) {
        //获取分页信息
        Integer page = (Integer) objectMap.get("page");
        Integer answerArticle = (Integer) objectMap.get("answerArticle");
        //构建条件构造器
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("answer_article", answerArticle);
        //获取回答列表
        List<AnswerArticleDO> listAnswerArticle = listAnswerArticlePage(page, queryWrapper);
        //统计回答总数
        Integer listAnswerArticleCount = listAnswerArticleCount(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listAnswerArticle", listAnswerArticle);
        hashMap.put("listAnswerArticleCount", listAnswerArticleCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> listAnswerArticleBySearch(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        Integer answerArticle = (Integer) objectMap.get("answerArticle");
        String answerArticleId = (String) objectMap.get("answerArticleId");
        String questionId = (String) objectMap.get("questionId");
        String userId = (String) objectMap.get("userId");
        String content = (String) objectMap.get("content");
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("answer_article", answerArticle)
                .like(answerArticleId != null, "id", answerArticleId)
                .like(questionId != null, "question_id", questionId)
                .like(userId != null, "user_id", userId)
                .like(content != null, "content", content);
        List<AnswerArticleDO> listAnswerArticleSearch = listAnswerArticlePage(page, queryWrapper);
        Integer listAnswerArticleSearchCount = listAnswerArticleCount(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listAnswerArticleSearch", listAnswerArticleSearch);
        hashMap.put("listAnswerArticleSearchCount", listAnswerArticleSearchCount);
        return hashMap;
    }

    public List<AnswerArticleDO> listAnswerArticlePage(Integer page, QueryWrapper<AnswerArticleDO> queryWrapper) {
        Page<AnswerArticleDO> doPage = new Page<>(page, 10);
        queryWrapper
                .orderByDesc("update_time");
        return answerArticleDao.selectPage(doPage, queryWrapper).getRecords();
    }

    public Integer listAnswerArticleCount(QueryWrapper<AnswerArticleDO> queryWrapper) {
        return answerArticleDao.selectCount(queryWrapper);
    }

    @Override
    public HashMap<String, Object> saveAnswerArticle(AnswerArticleDO answerArticleDO) {
        answerArticleDO.setUpdateTime(LocalDateTime.now());
        int i = answerArticleDao.updateById(answerArticleDO);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        if (i == 1) {
            hashMap.put("saveAnswerArticleResult", true);
        } else {
            hashMap.put("saveAnswerArticleResult", true);
        }
        return hashMap;
    }


}
