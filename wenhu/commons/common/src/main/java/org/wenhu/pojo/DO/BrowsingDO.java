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
@TableName(value = "browsing")
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
