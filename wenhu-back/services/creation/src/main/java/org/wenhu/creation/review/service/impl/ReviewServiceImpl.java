package org.wenhu.creation.review.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.ReplyDO;
import org.wenhu.common.pojo.DO.ReviewDO;
import org.wenhu.common.pojo.DTO.ReplyDTO;
import org.wenhu.common.pojo.DTO.ReviewDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.creation.review.service.ReviewService;
import org.wenhu.database.dao.ReplyDao;
import org.wenhu.database.dao.ReviewDao;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/9
 */

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private ReplyDao replyDao;

    @GlobalTransactional
    @Override
    public Result<List<ReviewDTO>> getReviewById(String id) {
        List<ReviewDTO> reviewList = reviewDao.listReviewById(id);
        return Result.succeed(reviewList);
    }

    @GlobalTransactional
    @Override
    public Result<Integer> getReviewCount(String id) {
        QueryWrapper<ReviewDO> reviewQueryWrapper = new QueryWrapper<>();
        reviewQueryWrapper.eq("answer_id", id);
        Integer integer = reviewDao.selectCount(reviewQueryWrapper);

        return Result.succeed(integer);
    }

    @GlobalTransactional
    @Override
    public Result<List<ReviewDTO>> saveReview(String id, String userId, String reviewContent) {
        ReviewDO reviewDO = new ReviewDO()
                .setId(String.valueOf(SnowflakeUtils.genId()))
                .setAnswerId(id)
                .setReviewerId(userId)
                .setReviewContent(reviewContent)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setIsDeleted(0);
        reviewDao.insert(reviewDO);
        return getReviewById(id);
    }

    @GlobalTransactional
    @Override
    public Result<List<ReplyDTO>> getReplyByReviewId(String id) {
        List<ReplyDTO> replyList = replyDao.listReplyById(id);
        return Result.succeed(replyList);
    }

    @GlobalTransactional
    @Override
    public Result<List<ReplyDTO>> saveReply(String parentReviewId, String userId, String byReplierId, String replyContent) {
        ReplyDO replyDO = new ReplyDO()
                .setId(String.valueOf(SnowflakeUtils.genId()))
                .setParentReviewId(parentReviewId)
                .setReplierId(userId)
                .setByReplierId(byReplierId)
                .setReplyContent(replyContent)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setIsDeleted(0);
        replyDao.insert(replyDO);
        return getReplyByReviewId(parentReviewId);
    }
}
