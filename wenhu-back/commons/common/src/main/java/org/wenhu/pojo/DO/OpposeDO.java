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
@TableName(value = "oppose")
public class OpposeDO {

    /**
     * 热榜id
     */

    private String id;

    /**
     * 回答OR文章id
     */
    private String parentId;

    /**
     * 用户id
     */
    private String userId;

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
