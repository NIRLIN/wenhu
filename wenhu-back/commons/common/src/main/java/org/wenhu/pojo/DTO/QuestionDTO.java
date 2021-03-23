package org.wenhu.pojo.DTO;


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
    private String followNumber;

    /**
     * 浏览数
     */
    private String browseNumber;

}
