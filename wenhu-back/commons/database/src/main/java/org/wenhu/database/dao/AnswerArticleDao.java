package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface AnswerArticleDao extends BaseMapper<AnswerArticleDO> {

    @Select("SELECT DISTINCT\n" +
            "\tanswer_article.id,\n" +
            "\tanswer_article.question_id,\n" +
            "\tanswer_article.answer_article,\n" +
            "\tanswer_article.user_id,\n" +
            "\tanswer_article.content,\n" +
            "\tanswer_article.approval_number,\n" +
            "\tanswer_article.oppose_number,\n" +
            "\tanswer_article.update_time,\n" +
            "\tanswer_article.is_deleted,\n" +
            "\t`user`.username,\n" +
            "\t`user`.resume,\n" +
            "\t`user`.head_image \n" +
            "FROM\n" +
            "\tanswer_article\n" +
            "\tLEFT JOIN `user` ON `user`.id = answer_article.user_id \n" +
            "WHERE\n" +
            "\tanswer_article.answer_article = 0 \n" +
            "\tAND answer_article.id =#{articleId}")
    AnswerArticleDTO selectArticleById(@Param("articleId") String articleId);
}
