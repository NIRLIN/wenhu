package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DTO.AnswerDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface AnswerDao extends BaseMapper<AnswerDO> {

    @Select("  SELECT DISTINCT\n" +
            "  answer.id,\n" +
            "  answer.question_id,\n" +
            "\tquestion.title,\n" +
            "  answer.user_id,\n" +
            "  answer.content,\n" +
            "  answer.approval_number,\n" +
            "  answer.oppose_number,\n" +
            "  answer.update_time,\n" +
            "  answer.is_deleted,\n" +
            "  `user`.username,\n" +
            "  `user`.resume,\n" +
            "  `user`.head_image \n" +
            "  FROM\n" +
            "  answer\n" +
            "  LEFT JOIN `user` ON `user`.id = answer.user_id \n" +
            "\tLEFT JOIN question ON question.id = answer.question_id\n" +
            "  WHERE\n" +
            "  answer.answer = 1\n" +
            "  AND answer.user_id=#{userId}\n" +
            "\tORDER BY answer.update_time DESC")
    List<AnswerDTO> listAnswerByTime(@Param("userId") String userId);

    @Select("  SELECT DISTINCT\n" +
            "  answer.id,\n" +
            "  answer.question_id,\n" +
            "\tquestion.title,\n" +
            "  answer.user_id,\n" +
            "  answer.content,\n" +
            "  answer.approval_number,\n" +
            "  answer.oppose_number,\n" +
            "  answer.update_time,\n" +
            "  answer.is_deleted,\n" +
            "  `user`.username,\n" +
            "  `user`.resume,\n" +
            "  `user`.head_image \n" +
            "  FROM\n" +
            "  answer\n" +
            "  LEFT JOIN `user` ON `user`.id = answer.user_id \n" +
            "\tLEFT JOIN question ON question.id = answer.question_id\n" +
            "  WHERE\n" +
            "  answer.answer = 1\n" +
            "  AND answer.user_id=#{userId}\n" +
            "\tORDER BY answer.approval_number DESC")
    List<AnswerDTO> listAnswerByApproval(@Param("userId") String userId);
}
