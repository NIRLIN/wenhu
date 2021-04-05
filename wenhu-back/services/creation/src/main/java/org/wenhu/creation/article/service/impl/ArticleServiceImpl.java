package org.wenhu.creation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.AgreeOpposeDO;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.article.service.ArticleService;
import org.wenhu.database.dao.AgreeOpposeDao;
import org.wenhu.database.dao.AnswerArticleDao;
import org.wenhu.database.dao.UserDao;

import java.time.LocalDateTime;
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


    @Override
    public Result<List<AnswerArticleDTO>> listAnswerByUserId(String userId, String type) {
        //查询最近发布文章
        String queryByTime = "time";
        //查询热度最高文章
        String queryByHeat = "heat";
        QueryWrapper<AnswerArticleDO> queryWrapper = new QueryWrapper<>();
        //获取文章
        if (type == null||queryByTime.equals(type)) {
            //查询对应用户id的文章、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .eq( "answer_article", 0)
                    .orderByDesc("update_time");
        }
        if (queryByHeat.equals(type)) {
            //查询对应用户id的文章、根据赞同数排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .eq( "answer_article", 0)
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
                .eq("user_id",userId)
                .eq( "agree_oppose", 1)
                .eq("answer_article_id",answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        if (agreeOpposeDO!=null){
            return Result.succeed(true);
        }else {
            return Result.succeed(false);
        }
    }

    @Override
    public Result<Boolean> saveAgreeArticleByUserId(String userId, String answerId) {
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id",userId)
                .eq("answer_article_id",answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        if (agreeOpposeDO!=null){
            if (agreeOpposeDO.getAgreeOppose()==0){
                agreeOpposeDO.setAgreeOppose(1);
                QueryWrapper<AnswerArticleDO> doQueryWrapper = new QueryWrapper<>();
                doQueryWrapper
                        .eq("id",answerId)
                        .eq("answer_article",0);
                AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(doQueryWrapper);
                answerArticleDO.setUpdateTime(LocalDateTime.now());
                answerArticleDO.setApprovalNumber(String.valueOf(Integer.parseInt(answerArticleDO.getApprovalNumber())+1));
                int i = answerArticleDao.updateById(answerArticleDO);
            } else if (agreeOpposeDO.getAgreeOppose()==1){
                agreeOpposeDO.setAgreeOppose(0);
                QueryWrapper<AnswerArticleDO> doQueryWrapper = new QueryWrapper<>();
                doQueryWrapper
                        .eq("id",answerId)
                        .eq("answer_article",0);
                AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(doQueryWrapper);
                answerArticleDO.setUpdateTime(LocalDateTime.now());
                answerArticleDO.setApprovalNumber(String.valueOf(Integer.parseInt(answerArticleDO.getApprovalNumber())-1));
                int i = answerArticleDao.updateById(answerArticleDO);
            }
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDao.updateById(agreeOpposeDO);
        }else {
            agreeOpposeDO=new AgreeOpposeDO();
            agreeOpposeDO.setId(String.valueOf(SnowflakeUtils.genId()));
            agreeOpposeDO.setAnswerArticleId(answerId);
            agreeOpposeDO.setUserId(userId);
            agreeOpposeDO.setAgreeOppose(1);
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDO.setCreateTime(LocalDateTime.now());
            agreeOpposeDO.setIsDeleted(0);
            agreeOpposeDao.insert(agreeOpposeDO);
        }
        if (agreeOpposeDO.getAgreeOppose()==0){
            return Result.succeed(false);

        }else {
            return Result.succeed(true);
        }
    }

    @Override
    public Result<Boolean> saveOpposeArticleByUserId(String userId, String answerId) {
        QueryWrapper<AgreeOpposeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id",userId)
                .eq("answer_article_id",answerId);
        AgreeOpposeDO agreeOpposeDO = agreeOpposeDao.selectOne(queryWrapper);
        if (agreeOpposeDO!=null) {
            agreeOpposeDO.setAgreeOppose(0);
            agreeOpposeDO.setUpdateTime(LocalDateTime.now());
            agreeOpposeDao.updateById(agreeOpposeDO);
            QueryWrapper<AnswerArticleDO> doQueryWrapper = new QueryWrapper<>();
            doQueryWrapper
                    .eq("id",answerId)
                    .eq("answer_article",0);
            AnswerArticleDO answerArticleDO = answerArticleDao.selectOne(doQueryWrapper);
            answerArticleDO.setUpdateTime(LocalDateTime.now());
            answerArticleDO.setApprovalNumber(String.valueOf(Integer.parseInt(answerArticleDO.getApprovalNumber())-1));
            int i = answerArticleDao.updateById(answerArticleDO);
        }
        return Result.succeed();
    }
}
