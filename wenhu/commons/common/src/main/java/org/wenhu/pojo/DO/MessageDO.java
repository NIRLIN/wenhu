package org.wenhu.pojo.DO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class MessageDO {

    /**
     * 私信id
     */

    private String id;

    /**
     * 回话id
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
