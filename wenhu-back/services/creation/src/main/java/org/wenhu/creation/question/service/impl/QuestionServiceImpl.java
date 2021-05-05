package org.wenhu.creation.question.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DO.FollowQuestionDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DO.QuestionLogDO;
import org.wenhu.common.pojo.DTO.QuestionLogDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.question.service.QuestionService;
import org.wenhu.creation.util.FilterSensitivity;
import org.wenhu.database.dao.FollowQuestionDao;
import org.wenhu.database.dao.QuestionDao;
import org.wenhu.database.dao.QuestionLogDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private FollowQuestionDao followQuestionDao;
    @Autowired
    private FilterSensitivity filterSensitivity;

    @GlobalTransactional
    @Override
    public Result<String> saveQuestion(QuestionDO questionDO, String menderId) {
        String code;
        String message;
        String data = null;
        //插入问题
        questionDO.setId(String.valueOf(SnowflakeUtils.genId()));
        questionDO.setUserId(menderId);
        questionDO.setTitle(filterSensitivity.filterSensitiveWord(questionDO.getTitle()));
        questionDO.setDescription(filterSensitivity.filterSensitiveWord(questionDO.getDescription()));
        questionDO.setFollowNumber(0);
        questionDO.setCreateTime(LocalDateTime.now());
        questionDO.setUpdateTime(LocalDateTime.now());
        questionDO.setIsDeleted(0);
        int insertQuestionDO = questionDao.insert(questionDO);
        //插入问题日志
        QuestionLogDO questionLogDO = new QuestionLogDO();
        questionLogDO.setId(String.valueOf(SnowflakeUtils.genId()));
        questionLogDO.setMenderId(menderId);
        questionLogDO.setQuestionId(questionDO.getId());
        questionLogDO.setTitle(questionDO.getTitle());
        questionLogDO.setDescription(questionDO.getDescription());
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
    @GlobalTransactional
    public Result<QuestionDO> getQuestionById(String id) {
        String code;
        String message;
        QuestionDO data = null;
        QuestionDO questionDO = questionDao.selectById(id);
        if (questionDO != null) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
            data = questionDO;
        } else {
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
        }
        return Result.succeed(code, message, data);
    }

    @Override
    @GlobalTransactional
    public Result<List<QuestionDO>> listQuestionByUserId(@RequestBody UserDTO userDTO) {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("user_id", userDTO.getId());
        return Result.succeed(questionDao.selectByMap(hashMap));
    }


    @Override
    @GlobalTransactional
    public Result<Integer> userFollowQuestion(Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String questionId = (String) objectMap.get("questionId");
        QueryWrapper<FollowQuestionDO> followQuestionQueryWrapper = new QueryWrapper<>();
        followQuestionQueryWrapper
                .eq("is_deleted", 0)
                .eq("question_id", questionId)
                .eq("follower_id", userId);
        FollowQuestionDO followQuestionDO = followQuestionDao.selectOne(followQuestionQueryWrapper);
        if (followQuestionDO != null) {
            return Result.succeed(1);
        } else {
            return Result.succeed(0);
        }
    }

    @Override
    @GlobalTransactional
    public Result<Integer> followQuestion(Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String questionId = (String) objectMap.get("questionId");
        QueryWrapper<FollowQuestionDO> followQuestionQueryWrapper = new QueryWrapper<>();
        followQuestionQueryWrapper
                .eq("question_id", questionId)
                .eq("follower_id", userId);
        FollowQuestionDO followQuestionDO = followQuestionDao.selectOne(followQuestionQueryWrapper);
        //查询问题
        QueryWrapper<QuestionDO> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("id", questionId);
        QuestionDO questionDO = questionDao.selectOne(questionQueryWrapper);

        if (followQuestionDO != null) {
            if (followQuestionDO.getIsDeleted() == 0) {
                followQuestionDO.setIsDeleted(1);
                questionDO.setFollowNumber(questionDO.getFollowNumber() - 1);
            } else {
                followQuestionDO.setIsDeleted(0);
                questionDO.setFollowNumber(questionDO.getFollowNumber() + 1);
            }
            followQuestionDO.setUpdateTime(LocalDateTime.now());
            questionDO.setUpdateTime(LocalDateTime.now());
            questionDao.updateById(questionDO);
            int update = followQuestionDao.updateById(followQuestionDO);
            return Result.succeed(followQuestionDO.getIsDeleted() == 0 ? 1 : 0);
        } else {
            followQuestionDO = new FollowQuestionDO();
            followQuestionDO
                    .setId(String.valueOf(SnowflakeUtils.genId()))
                    .setQuestionId(questionId)
                    .setFollowerId(userId)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now())
                    .setIsDeleted(0);

            questionDO.setUpdateTime(LocalDateTime.now());
            questionDO.setFollowNumber(questionDO.getFollowNumber() + 1);
            questionDao.updateById(questionDO);
            int insert = followQuestionDao.insert(followQuestionDO);
            return Result.succeed(insert);
        }
    }

    @Override
    @GlobalTransactional
    public Result<List<QuestionLogDTO>> listQuestionLog(Map<String, Object> objectMap) {
        String questionId = (String) objectMap.get("id");
        List<QuestionLogDTO> listQuestionLog = questionLogDao.listQuestionLog(questionId);
        return Result.succeed(listQuestionLog);
    }

    @Override
    @GlobalTransactional
    public Result<Integer> updateQuestion(Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        String userId = (String) objectMap.get("userId");
        String title = (String) objectMap.get("title");
        String description = (String) objectMap.get("description");
        QueryWrapper<QuestionDO> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper
                .eq("id", id);
        QuestionDO questionDO = questionDao.selectOne(questionQueryWrapper);
        if (questionDO == null) {
            return Result.failed("问题查询不到");
        }
        questionDO
                .setUpdateTime(LocalDateTime.now())
                .setTitle(title)
                .setDescription(description);
        int i = questionDao.updateById(questionDO);
        if (i != 1) {
            return Result.failed("问题保存失败");
        }
        QuestionLogDO questionLogDO = new QuestionLogDO();
        QueryWrapper<QuestionLogDO> questionLogQueryWrapper = new QueryWrapper<>();
        questionLogDO
                .setId(String.valueOf(SnowflakeUtils.genId()))
                .setQuestionId(id)
                .setMenderId(userId)
                .setTitle(title)
                .setDescription(description)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setIsDeleted(0);
        int insert = questionLogDao.insert(questionLogDO);
        if (insert == 0) {
            return Result.failed("问题日志保存失败");
        }
        return Result.succeed("操作成功");
    }
}
