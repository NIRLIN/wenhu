package org.wenhu.common.pojo.DTO;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 */
@Data
@Accessors(chain = true)
public class FollowUserDTO {

    /**
     * 关注id
     */
    private String id;

    /**
     * 关注者id
     */
    private String followerId;

    /**
     * 被关注者id
     */
    private String byFollowerId;

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
