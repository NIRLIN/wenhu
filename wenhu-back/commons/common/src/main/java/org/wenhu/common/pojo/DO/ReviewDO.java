package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("review")
public class ReviewDO {

    /**
     * 评论id
     */
    private String id;

    /**
     * 文章或回答，1为回答，0为文章
     */
    private String answerArticleId;

    /**
     * 评论者id
     */
    private String reviewerId;
    /**
     * 评论内容
     */
    private String reviewContent;

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
