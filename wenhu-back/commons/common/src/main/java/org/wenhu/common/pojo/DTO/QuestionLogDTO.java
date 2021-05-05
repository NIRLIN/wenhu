package org.wenhu.common.pojo.DTO;


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
public class QuestionLogDTO {

    /**
     * 日志id
     */
    private String id;

    /**
     * 修改者id
     */
    private String menderId;

    /**
     * 修改者用户名
     */
    private String menderUsername;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String description;

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
