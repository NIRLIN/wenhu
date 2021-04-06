package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("report_message")
public class ReportMessageDO {

    /**
     * 举报id
     */
    private String id;

    /**
     * 举报者id
     */
    private String reporterId;

    /**
     * 私信id
     */
    private String messageId;

    /**
     * 举报理由
     */
    private String reportReason;

    /**
     * 举报详情
     */
    private String reportDetails;

    /**
     * 是否处理
     */
    private Integer isDispose;

    /**
     * 处理者id
     */
    private String disposerId;

    /**
     * 处理结果
     */
    private String disposeResult;

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
