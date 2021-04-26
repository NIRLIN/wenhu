package org.wenhu.search.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.AnswerArticleDO;
import org.wenhu.search.pojo.EsAnswer;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface AnswerDao extends BaseMapper<AnswerArticleDO> {

    @Select("  SELECT DISTINCT\n" +
            "  answer_article.id,\n" +
            "  answer_article.question_id,\n" +
            "\tquestion.title,\n" +
            "  answer_article.answer_article,\n" +
            "  answer_article.user_id,\n" +
            "  answer_article.content,\n" +
            "  answer_article.approval_number,\n" +
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
            "\tORDER BY answer_article.update_time DESC")
    List<EsAnswer> listAnswer();

}
