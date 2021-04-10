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
    Result<Integer> getReviewCount(String id);

    Result<List<ReviewDTO>> getReviewById(String id);

    Result<List<ReviewDTO>>  saveReview(String id, String userId, String reviewContent);

    Result<List<ReplyDTO>> getReplyByReviewId(String id);

    Result<List<ReplyDTO>> saveReply(String parentReviewId, String userId, String byReplierId, String replyContent);
}
