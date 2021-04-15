package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 * @Accessors(chain = true) 开启链式
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class UserDO {

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
    @TableLogic
    private Integer isDeleted;

}
