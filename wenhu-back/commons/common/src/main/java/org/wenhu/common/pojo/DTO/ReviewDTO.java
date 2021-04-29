package org.wenhu.common.pojo.DTO;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021-04-09
 * @Accessors(chain = true) 开启链式
 */
@Data
@Accessors(chain = true)
public class ReviewDTO {

    /**
     * 评论id
     */
    private String id;

    /**
     * 回答id
     */
    private String answerId;

    /**
     * 父评论id，默认为0，无父评论
     */
    private String parentReviewId;

    /**
     * 评论者id
     */
    private String reviewerId;

    /**
     * 评论者id
     */
    private String reviewerName;

    /**
     * 评论者image
     */
    private String reviewerImage;

    /**
     * 评论内容
     */
    private String reviewContent;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

}
