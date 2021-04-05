package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 */
@Data
@TableName("question")
public class QuestionDO {

    /**
     * 问题id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题描述
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
    @TableLogic
    private Integer isDeleted;

}
