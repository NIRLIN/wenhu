package org.wenhu.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
@TableName(value = "question_log")
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
    private Integer isDeleted;


}
