package org.wenhu.creation.answer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.common.pojo.VO.AnswerVO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.answer.service.AnswerService;
import org.wenhu.database.dao.AnswerDao;
import org.wenhu.database.dao.UserDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private UserDao userDao;


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
        answerDO.setContent(content);
        answerDO.setApprovalNumber("0");
        answerDO.setOpposeNumber("0");
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
    public Result<List<AnswerVO>> listAnswerByHeat(String questionId, String page) {
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        //查询对应问题id的回答、根据赞同数排序--降序
        queryWrapper
                .eq(questionId != null, "question_id", questionId)
                .orderByDesc("approval_number");
        //不传入userId

        return listAnswer(page, queryWrapper);
    }

    @Override
    public Result<List<AnswerVO>> listAnswerByTime(String questionId, String page) {
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        //查询对应问题id的回答、根据时间排序--降序
        queryWrapper
                .eq("question_id", questionId)
                .orderByDesc("update_time");
        //不传入userId
        return listAnswer(page, queryWrapper);

    }

    @Override
    public Result<List<AnswerVO>> listAnswer(String page, QueryWrapper<AnswerDO> queryWrapper) {
        String code;
        String message;
        Page<AnswerDO> doPage = new Page<>(Integer.parseInt(page), 10);
        Page<AnswerDO> selectPage = answerDao.selectPage(doPage, queryWrapper);
        if (selectPage == null) {
            //没有查询到回答时返回
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
            return Result.succeed(code, message);
        }
        List<AnswerVO> answerVOList = new ArrayList<>();

        //数据遍历
        for (AnswerDO answerDO : selectPage.getRecords()) {
            AnswerVO answerVO = new AnswerVO();
            answerVO.setAnswerId(answerDO.getId());
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
    public Result<List<AnswerVO>> listAnswerByUserId(String userId, String type) {
        //查询最近发布回答
        String queryByTime = "time";
        //查询热度最高回答
        String queryByHeat = "heat";
        QueryWrapper<AnswerDO> queryWrapper = new QueryWrapper<>();
        if (type == null) {
            //查询对应用户id的回答、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .orderByDesc("update_time");
        }
        if (queryByTime.equals(type)) {
            //查询对应用户id的回答、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .orderByDesc("update_time");
        }
        if (queryByHeat.equals(type)) {
            //查询对应用户id的回答、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .orderByDesc("approval_number");
        }
        String code;
        String message;
        List<AnswerDO> answerDOList = answerDao.selectList(queryWrapper);
        if (answerDOList == null) {
            //没有查询到回答时返回
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
            return Result.succeed(code, message);
        }
        List<AnswerVO> answerVOList = new ArrayList<>();

        //查出回答对应用户信息
        UserDO userDO = userDao.selectById(userId);

        //数据遍历
        for (AnswerDO answerDO : answerDOList) {
            AnswerVO answerVO = new AnswerVO();
            answerVO.setAnswerId(answerDO.getId());
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


}
