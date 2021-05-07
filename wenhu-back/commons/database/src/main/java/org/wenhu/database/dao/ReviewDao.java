package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.ReviewDO;
import org.wenhu.common.pojo.DTO.ReviewDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface ReviewDao extends BaseMapper<ReviewDO> {
    /**
     * 查询评论
     *
     * @param id 回答id
     * @return List<ReviewDTO>
     */
    @Select("SELECT\n" +
            "\treview.id,\n" +
            "\tanswer_id,\n" +
            "\treviewer_id,\n" +
            "\t`user`.username as reviewerName,\n" +
            "\t`user`.head_image as reviewerImage,\n" +
            "\treview_content,\n" +
            "\treview.update_time as updateTime,\n" +
            "\treview.is_deleted \n" +
            "FROM\n" +
            "\treview\n" +
            "\tLEFT JOIN `user` ON `user`.id = review.reviewer_id \n" +
            "WHERE\n" +
            "\tanswer_id = #{id}" +
            "\tORDER BY review.update_time ASC")
    List<ReviewDTO> listReviewById(@Param("id") String id);
}
