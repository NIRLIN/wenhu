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
@TableName("message")
public class MessageDO {

    /**
     * 私信id
     */
    private String id;

    /**
     * 会话id
     */
    private String dialogueId;

    /**
     * 发送方id
     */
    private String sendId;

    /**
     * 接收方id
     */
    private String receiveId;

    /**
     * 私信内容
     */
    private String content;

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
