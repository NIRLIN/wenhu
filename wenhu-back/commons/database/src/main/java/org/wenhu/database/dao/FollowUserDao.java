package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.FollowUserDO;
import org.wenhu.common.pojo.DTO.FollowUserDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface FollowUserDao extends BaseMapper<FollowUserDO> {

    @Select("SELECT DISTINCT\n" +
            "\tfollow_user.id,\n" +
            "\tfollow_user.follower_id,\n" +
            "\tfollow_user.by_follower_id,\n" +
            "\t`user`.username,\n" +
            "\t`user`.resume,\n" +
            "\t`user`.head_image \n" +
            "FROM\n" +
            "\tfollow_user\n" +
            "\tLEFT JOIN `user` ON `user`.id = follow_user.by_follower_id \n" +
            "WHERE\n" +
            "\tfollow_user.follower_id =#{userId} \n" +
            "\tAND follow_user.is_deleted = 0")
    List<FollowUserDTO> listFollow(@Param("userId") String userId);

    @Select("SELECT DISTINCT\n" +
            "\tfollow_user.id,\n" +
            "\tfollow_user.follower_id,\n" +
            "\tfollow_user.by_follower_id,\n" +
            "\t`user`.username,\n" +
            "\t`user`.resume,\n" +
            "\t`user`.head_image \n" +
            "FROM\n" +
            "\tfollow_user\n" +
            "\tLEFT JOIN `user` ON `user`.id = follow_user.follower_id \n" +
            "WHERE\n" +
            "\tfollow_user.by_follower_id =#{userId} \n" +
            "\tAND follow_user.is_deleted = 0")
    List<FollowUserDTO> listFans(@Param("userId") String userId);
}
