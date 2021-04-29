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
@TableName("answer")
public class AnswerDO {

    /**
     * 回答id
     */
    private String id;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 赞同数
     */
    private Integer approvalNumber;

    /**
     * 反对数
     */
    private Integer opposeNumber;

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
