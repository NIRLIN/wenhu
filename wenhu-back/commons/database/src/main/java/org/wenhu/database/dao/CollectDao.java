package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.CollectDO;
import org.wenhu.common.pojo.DTO.AnswerDTO;

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
            "\tanswer.id,\n" +
            "\tanswer.question_id,\n" +
            "\tcollect.user_id,\n" +
            "\tanswer.content,\n" +
            "\tanswer.approval_number,\n" +
            "\tanswer.oppose_number,\n" +
            "\tanswer.update_time,\n" +
            "\tanswer.is_deleted,\n" +
            "\t`user`.username,\n" +
            "\t`user`.resume,\n" +
            "\t`user`.head_image \n" +
            "FROM\n" +
            "\tcollect\n" +
            "\tLEFT JOIN answer ON collect.answer_id = answer.id\n" +
            "\tLEFT JOIN `user` ON `user`.id = collect.user_id \n" +
            "WHERE\n" +
            "\tcollect.user_id = #{userId} \n" +
            "\tAND collect.is_deleted =0")
    List<AnswerDTO> listCollect(@Param("userId") String userId);
}
