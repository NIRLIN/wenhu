package org.wenhu.creation.answer.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.*;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.answer.service.AnswerService;
import org.wenhu.database.dao.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerArticleDao answerArticleDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AgreeOpposeDao agreeOpposeDao;
    @Autowired
    private CollectDao collectDao;


    @Override
    public Result<Integer> countAnswerByQuestionId(String questionId) {
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("id")
                .eq("answer_article", 1)
                .eq(questionId != null, "question_id", questionId);
        Integer integer = answerArticleDao.selectCount(queryWrapper);
        return Result.succeed(integer);
    }

    @Override
    public Result<String> saveAnswer(String userId, String questionId, String content) {
        String code;
        String message;
        String data = null;
        AnswerArticleDO answerDO = new AnswerArticleDO();
        answerDO.setId(String.valueOf(SnowflakeUtils.genId()));
        answerDO.setQuestionId(questionId);
        answerDO.setUserId(userId);
        answerDO.setContent(content);
        answerDO.setApprovalNumber("0");
        answerDO.setOpposeNumber("0");
        answerDO.setCreateTime(LocalDateTime.now());
        answerDO.setUpdateTime(LocalDateTime.now());
        answerDO.setIsDeleted(0);
        int insert = answerArticleDao.insert(answerDO);
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
    public Result<List<AnswerArticleDTO>> listAnswerByHeat(String questionId, String page) {
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        //查询对应问题id的回答、根据赞同数排序--降序
        queryWrapper
                .eq(questionId != null, "question_id", questionId)
                .eq("answer_article", 1)

                .orderByDesc("approval_number");
        //不传入userId
        return listAnswer(page, queryWrapper);
    }

    @Override
    public Result<List<AnswerArticleDTO>> listAnswerByTime(String questionId, String page) {
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        //查询对应问题id的回答、根据时间排序--降序
        queryWrapper
                .eq("question_id", questionId)
                .eq("answer_article", 1)

                .orderByDesc("update_time");
        //不传入userId
        return listAnswer(page, queryWrapper);

    }

    @Override
    public Result<List<AnswerArticleDTO>> listAnswer(String page, QueryWrapper<AnswerArticleDO> queryWrapper) {
        String code;
        String message;
        Page<AnswerArticleDO> doPage = new Page<>(Integer.parseInt(page), 10);
        Page<AnswerArticleDO> selectPage = answerArticleDao.selectPage(doPage, queryWrapper);
        if (selectPage == null) {
            //没有查询到回答时返回
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
            return Result.succeed(code, message);
        }
        List<AnswerArticleDTO> answerVOList = new ArrayList<>();

        //数据遍历
        for (AnswerArticleDO answerDO : selectPage.getRecords()) {
            AnswerArticleDTO answerVO = new AnswerArticleDTO();
            answerVO.setId(answerDO.getId());
            answerVO.setAnswerArticle(answerDO.getAnswerArticle());
            answerVO.setQuestionId(answerDO.getQuestionId());
            answerVO.setUserId(answerDO.getUserId());
            answerVO.setContent(answerDO.getContent());
            answerVO.setApprovalNumber(answerDO.getApprovalNumber());
            answerVO.setOpposeNumber(answerDO.getOpposeNumber());
            answerVO.setUpdateTime(answerDO.getUpdateTime());
            //查出回答对应用户信息
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
    public Result<List<AnswerArticleDTO>> listAnswerByUserId(String userId, String type) {
        //查询最近发布回答
        String queryByTime = "time";
        //查询热度最高回答
        String queryByHeat = "heat";
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        if (type == null) {
            //查询对应用户id的回答、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .eq("answer_article", 1)
                    .orderByDesc("update_time");
        }
        if (queryByTime.equals(type)) {
            //查询对应用户id的回答、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .eq("answer_article", 1)
                    .orderByDesc("update_time");
        }
        if (queryByHeat.equals(type)) {
            //查询对应用户id的回答、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .eq("answer_article", 1)
                    .orderByDesc("approval_number");
        }
        String code;
        String message;
        List<AnswerArticleDO> answerDOList = answerArticleDao.selectList(queryWrapper);
        if (answerDOList == null) {
            //没有查询到回答时返回
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
            return Result.succeed(code, message);
        }
        List<AnswerArticleDTO> answerVOList = new ArrayList<>();

        //查出回答对应用户信息
        UserDO userDO = userDao.selectById(userId);

        //数据遍历
        for (AnswerArticleDO answerDO : answerDOList) {
            AnswerArticleDTO answerVO = new AnswerArticleDTO();
            answerVO.setId(answerDO.getId());
            answerVO.setAnswerArticle(answerDO.getAnswerArticle());
            answerVO.setQuestionId(answerDO.getQuestionId());
            answerVO.setUserId(answerDO.getUserId());
            answerVO.setContent(answerDO.getContent());
            answerVO.setApprovalNumber(answerDO.getApprovalNumber());
            answerVO.setOpposeNumber(answerDO.getOpposeNumber());
            answerVO.setUpdateTime(answerDO.getUpdateTime());
            //查出回答对应用户信息
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
    public Result<HashMap<String, Object>> getUserAgreeAndCollectAnswer(String userId, String answerId) {

        //查询是否用户赞同回答
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("agree_oppose", 1)
                .eq("is_deleted", 0)
                .eq("answer_article_id", answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);

        QueryWrapper<CollectDO> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper
                .eq("user_id", userId)
                .eq("answer_article", 1)
                .eq("is_deleted", 0)
                .eq("answer_article_id", answerId);
        CollectDO collectDO = collectDao.selectOne(collectQueryWrapper);

        //返回查询结果
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
        //查询是否已赞同
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        //查询是否逻辑删除，避免数据重复插入
        queryWrapper
                .eq("user_id", userId)
                .eq("agree_oppose", 1)
                .eq("answer_article_id", answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        QueryWrapper<AnswerArticleDO> doQueryWrapper = new QueryWrapper<>();
        doQueryWrapper
                .eq("id", answerId)
                .eq("answer_article", 1);
        AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(doQueryWrapper);

        //存在相应数据情况下进行修改
        if (agreeOpposeDO != null) {
            //未删除情况下，为已赞同，将要取消赞同
            if (agreeOpposeDO.getIsDeleted() == 0) {
                //逻辑删除，即为未赞同
                agreeOpposeDO.setIsDeleted(1);

                answerArticleDO.setUpdateTime(LocalDateTime.now());
                //修改赞同数
                answerArticleDO.setApprovalNumber(String.valueOf(Integer.parseInt(answerArticleDO.getApprovalNumber()) - 1));
                int i = answerArticleDao.updateById(answerArticleDO);
            } else if (agreeOpposeDO.getIsDeleted() == 1) {
                agreeOpposeDO.setIsDeleted(0);

                //修改赞同数
                answerArticleDO.setUpdateTime(LocalDateTime.now());
                answerArticleDO.setApprovalNumber(String.valueOf(Integer.parseInt(answerArticleDO.getApprovalNumber()) + 1));
                int i = answerArticleDao.updateById(answerArticleDO);
            }
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDao.updateById(agreeOpposeDO);
        } else {
            agreeOpposeDO = new AgreeOpposeDO()
                    .setId(String.valueOf(SnowflakeUtils.genId()))
                    .setAnswerArticleId(answerId)
                    .setUserId(userId)
                    .setAgreeOppose(1)
                    .setUpdateTime(LocalDateTime.now())
                    .setCreateTime(LocalDateTime.now())
                    .setIsDeleted(0);
            agreeOpposeDao.insert(agreeOpposeDO);
        }
        HashMap<String, Object> hashMap = new HashMap<>(3);
        //为0，代表赞同，为1，代表无赞同数据
        if (agreeOpposeDO.getIsDeleted() == 0) {
            hashMap.put("agreeBool", true);
        } else if (agreeOpposeDO.getIsDeleted() == 1) {
            hashMap.put("agreeBool", false);
        }
        hashMap.put("approval_number", answerArticleDO.getApprovalNumber());
        return Result.succeed(hashMap);
    }

    @Override
    public Result<HashMap<String, Object>> userOpposeAnswer(String userId, String answerId) {
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("agree_oppose", 1)
                .eq("answer_article_id", answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(3);
        QueryWrapper<AnswerArticleDO> doQueryWrapper = new QueryWrapper<>();
        doQueryWrapper
                .eq("id", answerId)
                .eq("answer_article", 1);
        AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(doQueryWrapper);
        if (agreeOpposeDO != null && agreeOpposeDO.getIsDeleted() == 0) {
            agreeOpposeDO.setIsDeleted(1);
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDao.updateById(agreeOpposeDO);
            answerArticleDO.setUpdateTime(LocalDateTime.now());
            answerArticleDO.setApprovalNumber(String.valueOf(Integer.parseInt(answerArticleDO.getApprovalNumber()) - 1));
            int i = answerArticleDao.updateById(answerArticleDO);
        }
        hashMap.put("approval_number", answerArticleDO.getApprovalNumber());
        hashMap.put("agreeBool", false);
        return Result.succeed(hashMap);
    }

    @Override
    public Result<HashMap<String, Object>> getAnswerByAnswerId(String answerId) {
        QueryWrapper<AnswerArticleDO> articleDoQueryWrapper = new QueryWrapper<>();
        articleDoQueryWrapper
                .eq("id", answerId)
                .eq("answer_article", 1);
        AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(articleDoQueryWrapper);
        QueryWrapper<QuestionDO> questionDoQueryWrapper = new QueryWrapper<>();
        questionDoQueryWrapper
                .eq("id", answerArticleDO.getQuestionId());
        QuestionDO questionDO = questionDao.selectOne(questionDoQueryWrapper);

        QueryWrapper<UserDO> userDoQueryWrapper = new QueryWrapper<>();
        userDoQueryWrapper
                .eq("id", answerArticleDO.getUserId());
        UserDO userDO = userDao.selectOne(userDoQueryWrapper);
        AnswerArticleDTO answerArticleDTO = new AnswerArticleDTO();
        BeanUtils.copyProperties(answerArticleDO, answerArticleDTO);
        answerArticleDTO.setUsername(userDO.getUsername());
        answerArticleDTO.setHeadImage(userDO.getHeadImage());
        answerArticleDTO.setResume(userDO.getResume());
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("answer", answerArticleDTO);
        hashMap.put("question", questionDO);
        return Result.succeed(hashMap);
    }
}
