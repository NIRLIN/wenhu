package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.ReplyDO;
import org.wenhu.common.pojo.DTO.ReplyDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface ReplyDao extends BaseMapper<ReplyDO> {
    /**
     * 查询回复信息
     *
     * @param parentReviewId 回复id
     * @return List<ReplyDTO>
     */
    @Select("SELECT\n" +
            "\treply.id AS id,\n" +
            "\tparent_review_id,\n" +
            "\treplier_id,\n" +
            "\tusera.username AS replierUsername,\n" +
            "\tusera.head_image AS replierHeadImage,\n" +
            "\tby_replier_id,\n" +
            "\tuserb.username AS byReplierIdUsername,\n" +
            "\tuserb.head_image AS byReplierIdHeadImage,\n" +
            "\treply_content,\n" +
            "\treply.update_time AS update_time,\n" +
            "\treply.is_deleted \n" +
            "FROM\n" +
            "\treply\n" +
            "\tLEFT JOIN `user` AS usera ON usera.id = reply.replier_id\n" +
            "\tLEFT JOIN `user` AS userb ON userb.id = reply.by_replier_id \n" +
            "WHERE\n" +
            "\tparent_review_id =#{parentReviewId} \n" +
            "ORDER BY\n" +
            "\treply.update_time ASC")
    List<ReplyDTO> listReplyById(@Param("parentReviewId") String parentReviewId);
}
