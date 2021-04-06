package org.wenhu.common.pojo.DTO;


import lombok.Data;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
public class QuestionDTO {

    /**
     * 问题id
     */

    private String id;

    /**
     * 用户id
     */

    private String userId;

    /**
     * 题目
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 关注数
     */
    private Integer followNumber;

    /**
     * 浏览数
     */
    private Integer browseNumber;

}
