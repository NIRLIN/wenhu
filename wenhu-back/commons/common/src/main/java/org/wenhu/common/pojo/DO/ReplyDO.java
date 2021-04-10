package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("reply")
public class ReplyDO {

    /**
     * 评论id
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
     * 回复者id
     */
    private String byReplierId;

    /**
     * 评论内容
     */
    private String replyContent;

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
