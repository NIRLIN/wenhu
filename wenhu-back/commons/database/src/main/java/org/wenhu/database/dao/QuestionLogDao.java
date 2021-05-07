package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.QuestionLogDO;
import org.wenhu.common.pojo.DTO.QuestionLogDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface QuestionLogDao extends BaseMapper<QuestionLogDO> {
    /**
     * 连表查询问题日志
     *
     * @param questionId 问题id
     * @return List<QuestionLogDTO>
     */
    @Select("SELECT\n" +
            "\tquestion_log.id AS id,\n" +
            "\tquestion_id,\n" +
            "\tmender_id AS menderId,\n" +
            "\t`user`.username AS menderUsername,\n" +
            "\tquestion_id AS questionId,\n" +
            "\ttitle,\n" +
            "\tdescription,\n" +
            "\tquestion_log.update_time AS updateTime \n" +
            "FROM\n" +
            "\tquestion_log\n" +
            "\tLEFT JOIN `user` ON `user`.id = question_log.mender_id \n" +
            "WHERE\n" +
            "\tquestion_log.is_deleted = 0 \n" +
            "\tAND question_log.question_id=#{questionId}\n" +
            "ORDER BY\n" +
            "\tquestion_log.update_time DESC")
    List<QuestionLogDTO> listQuestionLog(@Param("questionId") String questionId);
}
