package org.wenhu.common.pojo.DO;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/04/04
 */
@Data
@TableName("homepage")
public class HomepageDO {

    /**
     * 用户id
     */
    private String id;

    /**
     * 回答
     */
    private Integer answer;

    /**
     * 提问
     */
    private Integer question;

    /**
     * 文章
     */
    private Integer article;

    /**
     * 收藏
     */
    private Integer collect;

    /**
     * 关注
     */
    private Integer follow;

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
