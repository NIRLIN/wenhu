package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 */
@Data
@TableName("review")
public class ReviewDO {

    /**
     * 评论id
     */
    private String id;

    /**
     * 评论者id
     */
    private String reviewerId;

    /**
     * 评论内容
     */
    private String reviewContent;
    /**
     * 文章或回答，1为回答，0为文章
     */
    private String answerArticle;

    /**
     * 父回答OR文章id
     */
    private String parentId;

    /**
     * 父评论id
     */
    private String parentReviewId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDeleted;

}
