package org.wenhu.common.pojo.DTO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/18
 */

@Data
@Accessors(chain = true)
public class UserDTO {

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
     * 是否封禁
     */
    private Integer isBanned;

    /**
     * 封禁截止日期
     */
    private LocalDateTime deadlineDate;


}
