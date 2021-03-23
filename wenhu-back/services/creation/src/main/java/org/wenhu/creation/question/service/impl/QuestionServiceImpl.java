package org.wenhu.creation.question.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.creation.question.service.QuestionService;
import org.wenhu.dao.QuestionDao;
import org.wenhu.dao.QuestionLogDao;
import org.wenhu.pojo.DO.QuestionDO;
import org.wenhu.pojo.DO.QuestionLogDO;
import org.wenhu.pojo.DTO.QuestionDTO;
import org.wenhu.util.Result;
import org.wenhu.util.ResultCode;
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
    @Autowired
    private QuestionLogDao QuestionLogDao;

    @Override
    public Result<String> saveQuestion(QuestionDTO questionDTO, String menderId) {
        String code = null;
        String message = null;
        String data = null;
        //插入问题
        QuestionDO questionDO = new QuestionDO();
        questionDO.setId(String.valueOf(SnowflakeUtils.genId()));
        questionDO.setTitle(questionDTO.getTitle());
        questionDO.setDescription(questionDTO.getDescription());
        questionDO.setFollowNumber("0");
        questionDO.setBrowseNumber("0");
        questionDO.setCreateTime(LocalDateTime.now());
        questionDO.setUpdateTime(LocalDateTime.now());
        questionDO.setIsDeleted(0);
        System.out.println("questionDTO" + questionDTO);
        System.out.println("questionDO" + questionDO);
        int insertQuestionDO = questionDao.insert(questionDO);
        //插入问题日志
        QuestionLogDO questionLogDO = new QuestionLogDO();
        questionLogDO.setId(String.valueOf(SnowflakeUtils.genId()));
        questionLogDO.setMenderId(menderId);
        questionLogDO.setQuestionId(questionDO.getId());
        questionLogDO.setIsTitle("0");
        questionLogDO.setUpdateBeforeContent("");
        questionLogDO.setUpdateAfterContent(questionDO.getTitle());
        questionLogDO.setCreateTime(LocalDateTime.now());
        questionLogDO.setUpdateTime(LocalDateTime.now());
        questionLogDO.setIsDeleted(0);
        int insertQuestionLogDO = QuestionLogDao.insert(questionLogDO);
        if (insertQuestionDO == 1 && insertQuestionLogDO == 1) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
            data = "{\"id\":\"" + questionDO.getId() + "\"}";
        } else {
            code = ResultCode.OPERATION_FAIL_D0001.getCode();
            message = ResultCode.OPERATION_FAIL_D0001.getMessage();
        }
        return Result.succeed(code, message, data);
    }

}
