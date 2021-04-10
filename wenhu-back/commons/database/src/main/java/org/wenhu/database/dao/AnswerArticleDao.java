package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;

import java.util.List;

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

    @Select("  SELECT DISTINCT\n" +
            "  answer_article.id,\n" +
            "  answer_article.question_id,\n" +
            "\tquestion.title,\n" +
            "  answer_article.answer_article,\n" +
            "  answer_article.user_id,\n" +
            "  answer_article.content,\n" +
            "  answer_article.approval_number,\n" +
            "  answer_article.oppose_number,\n" +
            "  answer_article.update_time,\n" +
            "  answer_article.is_deleted,\n" +
            "  `user`.username,\n" +
            "  `user`.resume,\n" +
            "  `user`.head_image \n" +
            "  FROM\n" +
            "  answer_article\n" +
            "  LEFT JOIN `user` ON `user`.id = answer_article.user_id \n" +
            "\tLEFT JOIN question ON question.id = answer_article.question_id\n" +
            "  WHERE\n" +
            "  answer_article.answer_article = 1\n" +
            "  AND answer_article.user_id=#{userId}\n" +
            "\tORDER BY answer_article.update_time DESC")
    List<AnswerArticleDTO> listAnswerByTime(@Param("userId") String userId);

    @Select("  SELECT DISTINCT\n" +
            "  answer_article.id,\n" +
            "  answer_article.question_id,\n" +
            "\tquestion.title,\n" +
            "  answer_article.answer_article,\n" +
            "  answer_article.user_id,\n" +
            "  answer_article.content,\n" +
            "  answer_article.approval_number,\n" +
            "  answer_article.oppose_number,\n" +
            "  answer_article.update_time,\n" +
            "  answer_article.is_deleted,\n" +
            "  `user`.username,\n" +
            "  `user`.resume,\n" +
            "  `user`.head_image \n" +
            "  FROM\n" +
            "  answer_article\n" +
            "  LEFT JOIN `user` ON `user`.id = answer_article.user_id \n" +
            "\tLEFT JOIN question ON question.id = answer_article.question_id\n" +
            "  WHERE\n" +
            "  answer_article.answer_article = 1\n" +
            "  AND answer_article.user_id=#{userId}\n" +
            "\tORDER BY answer_article.approval_number DESC")
    List<AnswerArticleDTO> listAnswerByApproval(@Param("userId") String userId);
}
