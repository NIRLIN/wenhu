package org.wenhu.common.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Data
@TableName(value = "favorite")
public class FavoriteDO {
    /**
     * 收藏夹id
     */

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收藏夹名字
     */
    private String favoriteName;

    /**
     * 收藏数量
     */
    private String collectNumber;

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
