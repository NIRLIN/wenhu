package org.wenhu.common.pojo.DO;


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
@TableName("hot")
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
     * 分类id
     */
    private String classifyId;

    /**
     * 问题热度
     */
    private Integer questionHeat;

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
    private Integer isDeleted;

}
