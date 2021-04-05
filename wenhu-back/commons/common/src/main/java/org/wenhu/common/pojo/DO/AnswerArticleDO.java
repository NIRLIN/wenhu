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
@TableName("answer_article")
public class AnswerArticleDO {

    /**
     * 回答id
     */
    private String id;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 赞同或反对，1为回答，0为文章
     */
    private Integer answerArticle;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 赞同数
     */
    private String approvalNumber;

    /**
     * 反对数
     */
    private String opposeNumber;

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
