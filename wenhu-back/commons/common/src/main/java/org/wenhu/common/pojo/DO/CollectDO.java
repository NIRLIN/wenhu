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
@TableName(value = "collect")
public class CollectDO {
    /**
     * 收藏id
     */

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收藏夹id
     */
    private String favoriteId;

    /**
     * 内容id
     */
    private String contentId;

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
