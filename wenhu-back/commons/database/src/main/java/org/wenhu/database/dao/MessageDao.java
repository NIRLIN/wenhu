package org.wenhu.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.wenhu.common.pojo.DO.MessageDO;
import org.wenhu.common.pojo.DTO.MessageDTO;
import org.wenhu.common.pojo.DTO.MessageUserDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Mapper
@Repository
public interface MessageDao extends BaseMapper<MessageDO> {
    @Select("SELECT DISTINCT\n" +
            "\tmessage.id,\n" +
            "\tmessage.dialogue_id,\n" +
            "\tmessage.send_id,\n" +
            "\tmessage.receive_id,\n" +
            "\t`user`.username AS receiveUsername,\n" +
            "\t`user`.head_image AS receiveHeadImage,\n" +
            "\tmessage.content,\n" +
            "\tmessage.create_time,\n" +
            "\tmessage.update_time,\n" +
            "\tmessage.is_deleted \n" +
            "FROM\n" +
            "\tmessage\n" +
            "\tLEFT JOIN `user` ON `user`.id = message.receive_id \n" +
            "WHERE\n" +
            "\tmessage.send_id = #{userId} \n" +
            "ORDER BY\n" +
            "\tmessage.update_time DESC")
    List<MessageUserDTO> messageUserList(@Param("userId") String userId);

    @Select("SELECT DISTINCT\n" +
            "\tmessage.id,\n" +
            "\tmessage.dialogue_id,\n" +
            "\tmessage.send_id,\n" +
            "\tmessage.receive_id,\n" +
            "\tmessage.content,\n" +
            "\tmessage.create_time,\n" +
            "\tmessage.update_time,\n" +
            "\tmessage.is_deleted,\n" +
            "\tusera.id AS sendId,\n" +
            "\tusera.username AS sendUsername,\n" +
            "\tusera.head_image AS sendHeadImage,\n" +
            "\tuserb.id AS receiveId,\n" +
            "\tuserb.username AS receiveUsername,\n" +
            "\tuserb.head_image AS receiveHeadImage \n" +
            "FROM\n" +
            "\tmessage\n" +
            "\tLEFT JOIN `user` AS usera ON usera.id = message.send_id\n" +
            "\tLEFT JOIN `user` AS userb ON userb.id = message.receive_id \n" +
            "WHERE\n" +
            "\tmessage.dialogue_id = #{dialogueId} \n" +
            "ORDER BY\n" +
            "\tmessage.update_time ASC")
    List<MessageDTO> messageOneUserList(@Param("dialogueId") String dialogueId);

    @Select("SELECT DISTINCT\n" +
            "\tmessage.id,\n" +
            "\tmessage.dialogue_id,\n" +
            "\tmessage.send_id,\n" +
            "\tmessage.receive_id,\n" +
            "\t`user`.username AS receiveUsername,\n" +
            "\t`user`.head_image AS receiveHeadImage,\n" +
            "\tmessage.content,\n" +
            "\tmessage.create_time,\n" +
            "\tmessage.update_time,\n" +
            "\tmessage.is_deleted \n" +
            "FROM\n" +
            "\tmessage\n" +
            "\tLEFT JOIN `user` ON `user`.id = message.receive_id \n" +
            "WHERE\n" +
            "\tmessage.dialogue_id = #{dialogueId} \n" +
            "ORDER BY\n" +
            "\tmessage.update_time DESC")
    List<MessageUserDTO> selectUserByDialogueId(@Param("dialogueId") String dialogueId);
}
