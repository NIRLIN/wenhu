package org.wenhu.creation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.AgreeOpposeDO;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.common.pojo.DO.CollectDO;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.article.service.ArticleService;
import org.wenhu.database.dao.AgreeOpposeDao;
import org.wenhu.database.dao.AnswerArticleDao;
import org.wenhu.database.dao.CollectDao;
import org.wenhu.database.dao.UserDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private AnswerArticleDao answerArticleDao;
    @Autowired
    private AgreeOpposeDao agreeOpposeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CollectDao collectDao;


    @Override
    public Result<List<AnswerArticleDTO>> listAnswerByUserId(String userId, String type) {
        //查询最近发布文章
        String queryByTime = "time";
        //查询热度最高文章
        String queryByHeat = "heat";
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        //获取文章
        if (type == null || queryByTime.equals(type)) {
            //查询对应用户id的文章、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .eq("answer_article", 0)
                    .orderByDesc("update_time");
        }
        if (queryByHeat.equals(type)) {
            //查询对应用户id的文章、根据赞同数排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .eq("answer_article", 0)
                    .orderByDesc("approval_number");
        }
        return listArticle(userId, queryWrapper);
    }


    public Result<List<AnswerArticleDTO>> listArticle(String userId, QueryWrapper<AnswerArticleDO> queryWrapper) {
        String code;
        String message;
        List<AnswerArticleDO> articleDOList = answerArticleDao.selectList(queryWrapper);
        if (articleDOList == null) {
            //没有查询到文章时返回
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
            return Result.succeed(code, message);
        }
        UserDO userDO = userDao.selectById(userId);
        List<AnswerArticleDTO> articleVOList = articleDOList.stream().map(t -> {
            AnswerArticleDTO articleVO = new AnswerArticleDTO();
            articleVO.setAnswerArticle(t.getAnswerArticle());
            articleVO.setUserId(t.getUserId());
            articleVO.setContent(t.getContent());
            articleVO.setApprovalNumber(t.getApprovalNumber());
            articleVO.setOpposeNumber(t.getOpposeNumber());
            articleVO.setUpdateTime(t.getUpdateTime());
            //查出回答对应用户信息
            articleVO.setUsername(userDO.getUsername());
            articleVO.setResume(userDO.getResume());
            articleVO.setHeadImage(userDO.getHeadImage());
            return articleVO;
        }).collect(Collectors.toList());
        //数据遍历
        //查出回答对应用户信息
        return Result.succeed(articleVOList);
    }

    @Override
    public Result<Boolean> getUserAgreeArticle(String userId, String answerId) {
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("agree_oppose", 1)
                .eq("is_deleted", 0)
                .eq("answer_article_id", answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        if (agreeOpposeDO != null) {
            return Result.succeed(true);
        } else {
            return Result.succeed(false);
        }
    }

    @Override
    public Result<HashMap<String, Object>> getUserCollectArticle(String userId, String answerId) {
        QueryWrapper<CollectDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("answer_article", 0)
                .eq("is_deleted", 0)
                .eq("answer_article_id", answerId);
        CollectDO collectDO = collectDao.selectOne(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        if (collectDO != null) {
            hashMap.put("collectResult", true);
            return Result.succeed(hashMap);
        } else {
            hashMap.put("collectResult", false);
            return Result.succeed(hashMap);
        }
    }

    @Override
    public Result<HashMap<String, Object>> userAgreeArticle(String userId, String answerId) {
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
                .eq("answer_article", 0);
        AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(doQueryWrapper);

        //存在相应数据情况下进行修改
        if (agreeOpposeDO != null) {
            //未删除情况下，为已赞同，将要取消赞同
            if (agreeOpposeDO.getIsDeleted() == 0) {
                //逻辑删除，即为未赞同
                agreeOpposeDO.setIsDeleted(1);


                //修改赞同数
                answerArticleDO.setApprovalNumber(answerArticleDO.getApprovalNumber() - 1);
            } else if (agreeOpposeDO.getIsDeleted() == 1) {
                agreeOpposeDO.setIsDeleted(0);

                //修改赞同数
                answerArticleDO.setApprovalNumber(answerArticleDO.getApprovalNumber() + 1);
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
        answerArticleDao.updateById(answerArticleDO);
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
    public Result<HashMap<String, Object>> userOpposeArticle(String userId, String answerId) {
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
                .eq("answer_article", 0);
        AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(doQueryWrapper);
        if (agreeOpposeDO != null && agreeOpposeDO.getIsDeleted() == 0) {
            agreeOpposeDO.setIsDeleted(1);
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDao.updateById(agreeOpposeDO);

            answerArticleDO.setApprovalNumber(answerArticleDO.getApprovalNumber() - 1);
            answerArticleDao.updateById(answerArticleDO);
        }
        hashMap.put("approval_number", answerArticleDO.getApprovalNumber());
        hashMap.put("agreeBool", false);
        return Result.succeed(hashMap);
    }

    @Override
    public Result<AnswerArticleDTO> getArticleByArticleId(String articleId) {
        AnswerArticleDTO answerArticleDTO = answerArticleDao.selectArticleById(articleId);
        if (answerArticleDTO != null) {
            return Result.succeed(answerArticleDTO);
        } else {
            return Result.succeed(ResultCode.NO_FOUND_DATA.getCode(), ResultCode.NO_FOUND_DATA.getMessage(), new AnswerArticleDTO());
        }
    }
}
