package org.wenhu.pojo.DO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class QuestionDO {

    /**
     * 问题id
     */

    private String id;

    /**
     * 题目
     */
    private String topic;

    /**
     * 描述
     */
    private String description;

    /**
     * 关注数
     */
    private String followNumber;

    /**
     * 浏览数
     */
    private String browseNumber;

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
