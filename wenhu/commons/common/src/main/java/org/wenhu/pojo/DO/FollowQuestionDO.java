package org.wenhu.pojo.DO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class FollowQuestionDO {

    /**
     * 热榜id
     */
    private String id;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 关注者id
     */
    private String followerId;

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
