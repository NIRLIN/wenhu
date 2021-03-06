package org.wenhu.creation.answer.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.*;
import org.wenhu.common.pojo.DTO.AnswerDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.answer.service.AnswerService;
import org.wenhu.creation.util.FilterSensitivity;
import org.wenhu.database.dao.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AgreeOpposeDao agreeOpposeDao;
    @Autowired
    private CollectDao collectDao;
    @Autowired
    private FilterSensitivity filterSensitivity;


    @Override
    public Result<Integer> countAnswerByQuestionId(String questionId) {
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("id")
                .eq(questionId != null, "question_id", questionId);
        Integer integer = answerDao.selectCount(queryWrapper);
        return Result.succeed(integer);
    }


    @Override
    public Result<String> saveAnswer(String userId, String questionId, String content) {
        String code;
        String message;
        String data = null;
        AnswerDO answerDO = new AnswerDO();
        answerDO.setId(String.valueOf(SnowflakeUtils.genId()));
        answerDO.setQuestionId(questionId);
        answerDO.setUserId(userId);
        answerDO.setContent(filterSensitivity.filterSensitiveWord(content));
        answerDO.setApprovalNumber(0);
        answerDO.setOpposeNumber(0);
        answerDO.setCreateTime(LocalDateTime.now());
        answerDO.setUpdateTime(LocalDateTime.now());
        answerDO.setIsDeleted(0);
        int insert = answerDao.insert(answerDO);
        if (insert == 1) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
            data = answerDO.getId();
        } else if (insert == 0) {
            code = ResultCode.OPERATION_FAIL_D0001.getCode();
            message = ResultCode.OPERATION_FAIL_D0001.getMessage();
        } else {
            code = ResultCode.UNKNOWN_ERROR.getCode();
            message = ResultCode.UNKNOWN_ERROR.getMessage();
        }
        return Result.succeed(code, message, data);
    }


    @Override
    public Result<List<AnswerDTO>> listAnswerByHeat(String questionId, String page) {
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        //??????????????????id?????????????????????????????????--??????
        queryWrapper
                .eq(questionId != null, "question_id", questionId)
                .orderByDesc("approval_number");
        //?????????userId
        return listAnswer(page, queryWrapper);
    }


    @Override
    public Result<List<AnswerDTO>> listAnswerByTime(String questionId, String page) {
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        //??????????????????id??????????????????????????????--??????
        queryWrapper
                .eq("question_id", questionId)
                .orderByDesc("update_time");
        //?????????userId
        return listAnswer(page, queryWrapper);

    }

    @Override

    public Result<List<AnswerDTO>> listAnswer(String page, QueryWrapper<AnswerDO> queryWrapper) {
        String code;
        String message;
        Page<AnswerDO> doPage = new Page<>(Integer.parseInt(page), 10);
        Page<AnswerDO> selectPage = answerDao.selectPage(doPage, queryWrapper);
        if (selectPage == null) {
            //??????????????????????????????
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
            return Result.succeed(code, message);
        }
        List<AnswerDTO> answerVOList = new ArrayList<>();

        //????????????
        for (AnswerDO answerDO : selectPage.getRecords()) {
            AnswerDTO answerVO = new AnswerDTO();
            answerVO.setId(answerDO.getId());
            answerVO.setQuestionId(answerDO.getQuestionId());
            answerVO.setUserId(answerDO.getUserId());
            answerVO.setContent(answerDO.getContent());
            answerVO.setApprovalNumber(answerDO.getApprovalNumber());
            answerVO.setOpposeNumber(answerDO.getOpposeNumber());
            answerVO.setUpdateTime(answerDO.getUpdateTime());
            //??????????????????????????????
            UserDO userDO = userDao.selectById(answerDO.getUserId());
            if (userDO != null) {
                answerVO.setUsername(userDO.getUsername());
                answerVO.setResume(userDO.getResume());
                answerVO.setHeadImage(userDO.getHeadImage());
            }
            answerVOList.add(answerVO);
        }
        return Result.succeed(answerVOList);
    }


    @Override
    public Result<List<AnswerDTO>> listAnswerByUserId(String userId, String type) {
        //????????????????????????
        String queryByTime = "time";
        //????????????????????????
        String queryByHeat = "heat";
        List<AnswerDTO> answerDTOList = null;
        if (type == null) {
            //??????????????????id??????????????????????????????--??????
            answerDTOList = answerDao.listAnswerByTime(userId);
        }
        if (queryByTime.equals(type)) {
            //??????????????????id??????????????????????????????--??????
            answerDTOList = answerDao.listAnswerByTime(userId);
        }
        if (queryByHeat.equals(type)) {
            //??????????????????id??????????????????????????????--??????
            answerDTOList = answerDao.listAnswerByApproval(userId);
        }
        return Result.succeed(answerDTOList);
    }

    @Override

    public Result<HashMap<String, Object>> getUserAgreeAndCollectAnswer(String userId, String answerId) {

        //??????????????????????????????
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("agree_oppose", 1)
                .eq("is_deleted", 0)
                .eq("answer_id", answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);

        QueryWrapper<CollectDO> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper
                .eq("user_id", userId)
                .eq("is_deleted", 0)
                .eq("answer_id", answerId);
        CollectDO collectDO = collectDao.selectOne(collectQueryWrapper);

        //??????????????????
        HashMap<String, Object> hashMap = new HashMap<>(5);

        if (agreeOpposeDO != null) {
            hashMap.put("boolAgree", true);
        } else {
            hashMap.put("boolAgree", false);
        }
        if (collectDO != null) {
            hashMap.put("boolCollect", true);
        } else {
            hashMap.put("boolCollect", false);
        }
        return Result.succeed(hashMap);
    }

    @Override

    public Result<HashMap<String, Object>> userAgreeAnswer(String userId, String answerId) {
        //?????????????????????
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        //???????????????????????????????????????????????????
        queryWrapper
                .eq("user_id", userId)
                .eq("agree_oppose", 1)
                .eq("answer_id", answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        QueryWrapper<AnswerDO> doQueryWrapper = new QueryWrapper<>();
        doQueryWrapper
                .eq("id", answerId);
        AnswerDO answerDO = answerDao.selectOne(doQueryWrapper);

        //???????????????????????????????????????
        if (agreeOpposeDO != null) {
            //??????????????????????????????????????????????????????
            if (agreeOpposeDO.getIsDeleted() == 0) {
                //??????????????????????????????
                agreeOpposeDO.setIsDeleted(1);

                //????????????????????????
                answerDO.setApprovalNumber(answerDO.getApprovalNumber() - 1);
            } else {
                agreeOpposeDO.setIsDeleted(0);

                //????????????????????????

                answerDO.setApprovalNumber(answerDO.getApprovalNumber() + 1);
            }
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDao.updateById(agreeOpposeDO);
        } else {
            agreeOpposeDO = new AgreeOpposeDO()
                    .setId(String.valueOf(SnowflakeUtils.genId()))
                    .setUserId(userId)
                    .setAnswerId(answerId)
                    .setAgreeOppose(1)
                    .setUpdateTime(LocalDateTime.now())
                    .setCreateTime(LocalDateTime.now())
                    .setIsDeleted(0);
            agreeOpposeDao.insert(agreeOpposeDO);
            //??????????????????????????????????????????
            answerDO.setApprovalNumber(answerDO.getApprovalNumber() + 1);
        }
        answerDao.updateById(answerDO);
        HashMap<String, Object> hashMap = new HashMap<>(3);
        //???0?????????????????????1????????????????????????
        if (agreeOpposeDO.getIsDeleted() == 0) {
            hashMap.put("agreeBool", true);
        } else if (agreeOpposeDO.getIsDeleted() == 1) {
            hashMap.put("agreeBool", false);
        }
        hashMap.put("approval_number", answerDO.getApprovalNumber());
        return Result.succeed(hashMap);
    }

    @Override

    public Result<HashMap<String, Object>> userOpposeAnswer(String userId, String answerId) {
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("agree_oppose", 1)
                .eq("answer_id", answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(3);
        QueryWrapper<AnswerDO> doQueryWrapper = new QueryWrapper<>();
        doQueryWrapper
                .eq("id", answerId);
        AnswerDO answerDO = answerDao.selectOne(doQueryWrapper);
        if (agreeOpposeDO != null && agreeOpposeDO.getIsDeleted() == 0) {
            agreeOpposeDO.setIsDeleted(1);
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDao.updateById(agreeOpposeDO);

            answerDO.setApprovalNumber(answerDO.getApprovalNumber() - 1);
            answerDao.updateById(answerDO);
        }
        hashMap.put("approval_number", answerDO.getApprovalNumber());
        hashMap.put("agreeBool", false);
        return Result.succeed(hashMap);
    }

    @Override

    public Result<HashMap<String, Object>> getAnswerByAnswerId(String answerId) {
        QueryWrapper<AnswerDO> answerQueryWrapper = new QueryWrapper<>();
        answerQueryWrapper
                .eq("id", answerId);
        AnswerDO answerDO = answerDao.selectOne(answerQueryWrapper);
        QueryWrapper<QuestionDO> questionDoQueryWrapper = new QueryWrapper<>();
        questionDoQueryWrapper
                .eq("id", answerDO.getQuestionId());
        QuestionDO questionDO = questionDao.selectOne(questionDoQueryWrapper);

        QueryWrapper<UserDO> userDoQueryWrapper = new QueryWrapper<>();
        userDoQueryWrapper
                .eq("id", answerDO.getUserId());
        UserDO userDO = userDao.selectOne(userDoQueryWrapper);
        AnswerDTO answerDTO = new AnswerDTO();
        BeanUtils.copyProperties(answerDO, answerDTO);
        answerDTO.setUsername(userDO.getUsername());
        answerDTO.setHeadImage(userDO.getHeadImage());
        answerDTO.setResume(userDO.getResume());
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("answer", answerDTO);
        hashMap.put("question", questionDO);
        return Result.succeed(hashMap);
    }

    @Override

    public Result<Integer> updateAnswer(Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        String content = (String) objectMap.get("content");
        QueryWrapper<AnswerDO> answerQueryWrapper = new QueryWrapper<>();
        answerQueryWrapper.eq("id", id);
        AnswerDO answerDO = answerDao.selectOne(answerQueryWrapper);
        System.out.println(objectMap);
        System.out.println(id);
        System.out.println(answerDO);
        answerDO
                .setContent(content)
                .setUpdateTime(LocalDateTime.now());
        int i = answerDao.updateById(answerDO);
        return Result.succeed(i);
    }
}
