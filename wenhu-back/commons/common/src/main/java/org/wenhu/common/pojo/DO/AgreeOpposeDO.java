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
@TableName("agree_oppose")
public class AgreeOpposeDO {

    /**
     * 赞同id
     */
    private String id;

    /**
     * 回答OR文章id
     */
    private String answerArticleId;

    /**
     * 赞同或反对，1为赞同，0为反对
     */
    private Integer agreeOppose;

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
