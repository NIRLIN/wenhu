package org.wenhu.pojo.DO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class AdminDO {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 职位id
     */
    private String positionId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 个人简介
     */
    private String resume;

    /**
     * 个人头像
     */
    private String headImage;

    /**
     * 是否封禁
     */
    private Integer isBanned;

    /**
     * 封禁截止日期
     */
    private LocalDateTime deadlineDate;

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
