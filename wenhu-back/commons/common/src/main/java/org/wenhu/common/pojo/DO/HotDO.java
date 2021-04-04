package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
@TableName(value = "hot")
public class HotDO {

    /**
     * 热榜id
     */

    private String id;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 问题热度
     */
    private String questionHeat;

    /**
     * 问题标题
     */
    private String questionTitle;

    /**
     * 问题描述
     */
    private String questionDescription;

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
