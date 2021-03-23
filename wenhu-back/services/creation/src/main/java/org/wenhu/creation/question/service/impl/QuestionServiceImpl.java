package org.wenhu.creation.question.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.creation.question.service.QuestionService;
import org.wenhu.dao.QuestionDao;
import org.wenhu.dao.UserDao;
import org.wenhu.pojo.DO.QuestionDO;
import org.wenhu.pojo.DTO.QuestionDTO;
import org.wenhu.util.Result;
import org.wenhu.util.SnowflakeUtils;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/23
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public Result<String> saveQuestion(QuestionDTO questionDTO){
        System.out.println(questionDTO);
        QuestionDO questionDO = new QuestionDO();
        questionDO.setId(String.valueOf(SnowflakeUtils.genId()));
        questionDO.setTopic(questionDTO.getTopic());
        questionDO.setDescription(questionDO.getDescription());
        questionDO.setFollowNumber("0");
        questionDO.setBrowseNumber("0");
        questionDO.setCreateTime(LocalDateTime.now());
        questionDO.setUpdateTime(LocalDateTime.now());
        questionDO.setIsDeleted(0);
        int insert = questionDao.insert(questionDO);
        if (insert==1){
        }else {

        }
        return null;
    }

}
