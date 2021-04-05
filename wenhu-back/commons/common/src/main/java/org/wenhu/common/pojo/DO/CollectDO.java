package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 */
@Data
@TableName("collect")
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
     * 收藏回答或文章id
     */
    private String answerArticleId;

    /**
     * 文章或回答，1为回答，0为文章
     */
    private Integer answerArticle;


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
