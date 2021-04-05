package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.CollectDO;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Mapper
@Repository
public interface CollectDao extends BaseMapper<CollectDO> {
    @Select("SELECT DISTINCT\n" +
            "\tanswer_article.id,\n" +
            "\tanswer_article.question_id,\n" +
            "\tanswer_article.answer_article,\n" +
            "\tcollect.user_id,\n" +
            "\tanswer_article.content,\n" +
            "\tanswer_article.approval_number,\n" +
            "\tanswer_article.oppose_number,\n" +
            "\tanswer_article.update_time,\n" +
            "\tanswer_article.is_deleted,\n" +
            "\t`user`.username,\n" +
            "\t`user`.resume,\n" +
            "\t`user`.head_image \n" +
            "FROM\n" +
            "\tcollect\n" +
            "\tLEFT JOIN answer_article ON collect.answer_article_id = answer_article.id\n" +
            "\tLEFT JOIN `user` ON `user`.id = collect.user_id \n" +
            "WHERE\n" +
            "\tcollect.user_id = #{userId} \n" +
            "\tAND collect.answer_article =#{isAnswer}")
    List<AnswerArticleDTO> listCollect(@Param("userId") String userId, @Param("isAnswer") Integer isAnswer);
}
