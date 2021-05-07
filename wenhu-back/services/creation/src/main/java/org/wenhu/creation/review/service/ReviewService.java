package org.wenhu.creation.review.service;

import org.wenhu.common.pojo.DTO.ReplyDTO;
import org.wenhu.common.pojo.DTO.ReviewDTO;
import org.wenhu.common.util.Result;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/9
 */
public interface ReviewService {
    /**
     * 获取评论数量
     *
     * @param id id
     * @return 操作结果
     */
    Result<Integer> getReviewCount(String id);

    /**
     * 通过id获取评论
     *
     * @param id id
     * @return 操作结果
     */
    Result<List<ReviewDTO>> getReviewById(String id);

    /**
     * 保存评论
     *
     * @param id            id
     * @param userId        用户id
     * @param reviewContent 评论内容
     * @return 操作结果
     */
    Result<List<ReviewDTO>> saveReview(String id, String userId, String reviewContent);

    /**
     * 获取回复，通过评论id
     *
     * @param id id
     * @return 操作结果
     */
    Result<List<ReplyDTO>> getReplyByReviewId(String id);

    /**
     * 保存回复
     *
     * @param parentReviewId 评论id
     * @param userId         用户id
     * @param byReplierId    被回复者id
     * @param replyContent   回复内容
     * @return 操作结果
     */
    Result<List<ReplyDTO>> saveReply(String parentReviewId, String userId, String byReplierId, String replyContent);
}
