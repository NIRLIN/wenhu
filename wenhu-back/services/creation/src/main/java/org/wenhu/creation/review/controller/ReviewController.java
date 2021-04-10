package org.wenhu.creation.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DTO.ReplyDTO;
import org.wenhu.common.pojo.DTO.ReviewDTO;
import org.wenhu.common.util.Result;
import org.wenhu.creation.review.service.impl.ReviewServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/9
 */

@RestController
@RequestMapping(name = "review", value = "review")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewService;

    @PostMapping(name = "getReviewById", value = "getReviewById")
    Result<List<ReviewDTO>> getReviewById(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        return reviewService.getReviewById(id);
    }

    @PostMapping(name = "getReviewCount", value = "getReviewCount")
    Result<Integer> getReviewCount(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        return reviewService.getReviewCount(id);
    }
    @PostMapping(name = "saveReview", value = "saveReview")
    Result<List<ReviewDTO>>  saveReview(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        String userId = (String) objectMap.get("userId");
        String reviewContent = (String) objectMap.get("reviewContent");
        return reviewService.saveReview(id,userId,reviewContent);
    }
    @PostMapping(name = "getReplyByReviewId", value = "getReplyByReviewId")
    Result<List<ReplyDTO>> getReplyByReviewId(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        return reviewService.getReplyByReviewId(id);
    }
    @PostMapping(name = "saveReply", value = "saveReply")
    Result<List<ReplyDTO>>  saveReply(@RequestBody Map<String, Object> objectMap) {
        String parentReviewId = (String) objectMap.get("id");
        String userId = (String) objectMap.get("userId");
        String byReplierId= (String) objectMap.get("byReplierId");
        String replyContent = (String) objectMap.get("replyContent");
        return reviewService.saveReply(parentReviewId,userId,byReplierId,replyContent);
    }
}
