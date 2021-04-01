package org.wenhu.creation.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.ArticleDO;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.common.pojo.VO.ArticleVO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.creation.article.service.ArticleService;
import org.wenhu.database.dao.ArticleDao;
import org.wenhu.database.dao.UserDao;

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
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;


    @Override
    public Result<List<ArticleVO>> listAnswerByUserId(String userId, String type) {
        //查询最近发布文章
        String queryByTime = "time";
        //查询热度最高文章
        String queryByHeat = "heat";
        QueryWrapper<ArticleDO> queryWrapper = new QueryWrapper<>();
        //获取文章
        if (type == null) {
            //查询对应用户id的文章、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .orderByDesc("update_time");
        }
        if (queryByTime.equals(type)) {
            //查询对应用户id的文章、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .orderByDesc("update_time");
        }
        if (queryByHeat.equals(type)) {
            //查询对应用户id的文章、根据时间排序--降序
            queryWrapper
                    .eq(userId != null, "user_id", userId)
                    .orderByDesc("approval_number");
        }
        return listArticle(userId, queryWrapper);
    }


    public Result<List<ArticleVO>> listArticle(String userId, QueryWrapper<ArticleDO> queryWrapper) {
        String code;
        String message;
        List<ArticleDO> articleDOList = articleDao.selectList(queryWrapper);
        if (articleDOList == null) {
            //没有查询到文章时返回
            code = ResultCode.NO_FOUND_DATA.getCode();
            message = ResultCode.NO_FOUND_DATA.getMessage();
            return Result.succeed(code, message);
        }
        UserDO userDO = userDao.selectById(userId);
        List<ArticleVO> articleVOList = articleDOList.stream().map(t -> {
            ArticleVO articleVO = new ArticleVO();
            articleVO.setAnswerId(t.getId());
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
}
