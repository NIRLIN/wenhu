package org.wenhu.search.pojo;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/15
 */
@Data
@Accessors(chain = true)
@Document(indexName = "es_answer", shards = 3)
public class EsAnswer {

    /**
     * 赞同id
     */
    private String id;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 问题标题
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String title;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 个人简介
     */
    private String resume;

    /**
     * 个人头像
     */
    private String headImage;

    /**
     * 内容
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String content;

    /**
     * 赞同数
     */
    private Integer approvalNumber;

    /**
     * 修改时间
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

}
