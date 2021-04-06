package org.wenhu.common.pojo.DTO;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
@Accessors(chain = true)
public class HomepageDTO {

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


}
