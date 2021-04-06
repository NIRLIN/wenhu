package org.wenhu.creation.question.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DO.QuestionLogDO;
import org.wenhu.common.pojo.DTO.QuestionDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.question.service.QuestionService;
import org.wenhu.database.dao.QuestionDao;
import org.wenhu.database.dao.QuestionLogDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
    private QuestionLogDao questionLogDao;

    @Override
    public Result<String> saveQuestion(QuestionDTO questionDTO, String menderId) {
        String code;
        String message;
        String data = null;
        //插入问题
        QuestionDO questionDO = new QuestionDO();
        questionDO.setId(String.valueOf(SnowflakeUtils.genId()));
        questionDO.setUserId(menderId);
        questionDO.setTitle(questionDTO.getTitle());
        questionDO.setDescription(questionDTO.getDescription());
        questionDO.setFollowNumber(0);
        questionDO.setBrowseNumber(0);
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
        questionLogDO.setIsTitle(0);
        questionLogDO.setUpdateBeforeContent("");
        questionLogDO.setUpdateAfterContent(questionDO.getTitle());
        questionLogDO.setCreateTime(LocalDateTime.now());
        questionLogDO.setUpdateTime(LocalDateTime.now());
        questionLogDO.setIsDeleted(0);
        int insertQuestionLogDO = questionLogDao.insert(questionLogDO);
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

    @Override
    public Result<QuestionDTO> getQuestionById(String id) {
        String code;
        String message;
        QuestionDTO data = null;
        QuestionDO questionDO = questionDao.selectById(id);
        if (questionDO != null) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setId(questionDO.getId());
            questionDTO.setUserId(questionDO.getUserId());
            questionDTO.setTitle(questionDO.getTitle());
            questionDTO.setDescription(questionDO.getDescription());
            questionDTO.setBrowseNumber(questionDO.getBrowseNumber());
            questionDTO.setFollowNumber(questionDO.getFollowNumber());
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
            data = questionDTO;
        } else {
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
        }
        return Result.succeed(code, message, data);
    }

    @Override
    public Result<List<QuestionDO>> listQuestionByUserId(@RequestBody UserDTO userDTO) {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("user_id", userDTO.getId());
        return Result.succeed(questionDao.selectByMap(hashMap));
    }
}
