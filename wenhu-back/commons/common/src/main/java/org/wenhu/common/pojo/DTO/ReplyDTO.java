package org.wenhu.common.pojo.DTO;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021-04-10
 * @Accessors(chain = true) 开启链式
 */
@Data
@Accessors(chain = true)
public class ReplyDTO {

    /**
     * 回复id
     */
    private String id;

    /**
     * 父评论id，默认为0，无父评论
     */
    private String parentReviewId;

    /**
     * 回复者id
     */
    private String replierId;

    /**
     * 回复者用户名
     */
    private String replierUsername;

    /**
     * 回复者头像
     */
    private String replierHeadImage;

    /**
     * 被回复者id
     */
    private String byReplierId;

    /**
     * 被回复者用户名
     */
    private String byReplierIdUsername;

    /**
     * 被回复者头像
     */
    private String byReplierIdHeadImage;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

}
