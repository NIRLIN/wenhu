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
@TableName("question_log")
public class QuestionLogDO {

    /**
     * 日志id
     */
    private String id;

    /**
     * 修改者id
     */
    private String menderId;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 题目或描述,0为题目，1为描述
     */
    private Integer isTitle;

    /**
     * 修改前内容
     */
    private String updateBeforeContent;

    /**
     * 修改后内容
     */
    private String updateAfterContent;

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
