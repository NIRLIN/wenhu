package org.wenhu.common.pojo.DTO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
@TableName(value = "answer_article")
public class AnswerArticleDTO {

    /**
     * 回答id
     */

    private String id;

    /**
     * 问题id
     */
    private String questionId;


    /**
     * 用户id
     */
    private String userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 回答OR文章,1为回答，0为文章
     */
    private Integer isAnswer;

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
    private Integer isDeleted;


}
