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
@TableName("follow_user")
public class FollowUserDO {

    /**
     * 关注id
     */
    private String id;

    /**
     * 关注者id
     */
    private String followerId;

    /**
     * 被关注者id
     */
    private String byFollowerId;

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
