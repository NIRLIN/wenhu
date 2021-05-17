package org.wenhu.common.pojo.DTO;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 * @Accessors(chain = true) 开启链式
 */
@Data
@TableName("message")
public class MessageDTO {

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
     * 发送方用户名
     */
    private String sendUsername;
    /**
     * 发送方头像
     */
    private String sendHeadImage;

    /**
     * 接收方id
     */
    private String receiveId;
    /**
     * 接收方用户名
     */
    private String receiveUsername;
    /**
     * 接收方头像
     */
    private String receiveHeadImage;

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
