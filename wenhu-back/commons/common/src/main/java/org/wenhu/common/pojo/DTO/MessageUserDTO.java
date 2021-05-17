package org.wenhu.common.pojo.DTO;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 * @Accessors(chain = true) 开启链式
 */
@Data
@TableName("message")
public class MessageUserDTO {

    /**
     * 私信id
     */
    private String id;

    /**
     * 会话id
     */
    private String dialogueId;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


    private List<MessageDTO> messageList;

}
