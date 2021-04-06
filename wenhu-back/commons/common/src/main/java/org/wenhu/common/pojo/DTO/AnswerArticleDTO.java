package org.wenhu.common.pojo.DTO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class AnswerArticleDTO {

    /**
     * 赞同id
     */
    private String id;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 回答OR文章id,1为回答，0为文章
     */
    private Integer answerArticle;


    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 个人简介
     */
    private String resume;

    /**
     * 个人头像
     */
    private String headImage;

    /**
     * 内容
     */
    private String content;

    /**
     * 赞同数
     */
    private Integer approvalNumber;

    /**
     * 反对数
     */
    private Integer opposeNumber;

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
