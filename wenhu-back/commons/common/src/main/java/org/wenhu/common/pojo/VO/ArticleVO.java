package org.wenhu.common.pojo.VO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class ArticleVO {

    /**
     * 文章id
     */
    private String answerId;

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
    private String approvalNumber;

    /**
     * 反对数
     */
    private String opposeNumber;


    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

}
