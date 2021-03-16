package org.wenhu.pojo.DO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class BrowsingDO {

    /**
     * 记录id
     */

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 回答OR文章id
     */
    private String answerArticleId;

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
