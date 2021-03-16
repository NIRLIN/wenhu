package org.wenhu.pojo.DO;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class PositionDO {

    /**
     * 职位id
     */

    private String id;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 权限级别
     */
    private String power;

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
