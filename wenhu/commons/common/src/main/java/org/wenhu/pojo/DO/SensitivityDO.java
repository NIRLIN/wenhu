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
@TableName(value = "sensitivity")
public class SensitivityDO {

    /**
     * 敏感词id
     */

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 替换词
     */
    private String substitute;

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
