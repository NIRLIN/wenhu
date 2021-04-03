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
@TableName(value = "collect_article")
public class CollectArticleDO {
    /**
     * 文章收藏id
     */

    private String id;

    /**
     * 用户id
     */
    private String userId;


    /**
     * 文章id
     */
    private String articleId;

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
