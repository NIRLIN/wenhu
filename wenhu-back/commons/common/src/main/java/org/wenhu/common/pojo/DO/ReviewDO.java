package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
@TableName(value = "review")
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
    private Integer isDeleted;


}
