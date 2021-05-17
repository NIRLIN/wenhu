package org.wenhu.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.MessageDO;
import org.wenhu.common.pojo.DTO.MessageDTO;
import org.wenhu.common.pojo.DTO.MessageUserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.database.dao.MessageDao;
import org.wenhu.message.service.MessageService;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/5/13
 */

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public Object userMessageList(String userId) {
        List<MessageUserDTO> listMessageUser = messageDao.messageUserList(userId);
        System.out.println(listMessageUser);
        Set<String> idSet = new HashSet<>();
        for (MessageUserDTO messageUserDTO : listMessageUser) {
            idSet.add(messageUserDTO.getDialogueId());
        }
        listMessageUser = new ArrayList<>();
        for (String dialogueId : idSet) {
            List<MessageUserDTO> list = messageDao.selectUserByDialogueId(dialogueId);
            MessageUserDTO messageUserDTO = new MessageUserDTO();
            for (MessageUserDTO messageUserTmp : list) {
                if (!messageUserTmp.getReceiveId().equals(userId)) {
                    messageUserDTO = messageUserTmp;
                }
            }
            List<MessageDTO> messageList = messageDao.messageOneUserList(dialogueId);
            messageUserDTO.setMessageList(messageList);
            listMessageUser.add(messageUserDTO);
        }
        return Result.succeed(listMessageUser);
    }

    @Override
    public Object saveMessage(HashMap<String, Object> hashMap) {
        String userId = (String) hashMap.get("userId");
        String sendMessage = (String) hashMap.get("sendMessage");
        String receiveId = (String) hashMap.get("receiveId");
        MessageDO messageDO = new MessageDO();
        messageDO
                .setId(String.valueOf(SnowflakeUtils.genId()))
                .setContent(sendMessage)
                .setSendId(userId)
                .setReceiveId(receiveId)
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setIsDeleted(0);

        QueryWrapper<MessageDO> messageQueryWrapper = new QueryWrapper<>();
        messageQueryWrapper
                .eq("send_id", userId)
                .eq("receive_id", receiveId)
                .or()
                .eq("send_id", receiveId)
                .eq("receive_id", userId);
        List<MessageDO> messageList = messageDao.selectList(messageQueryWrapper);
        MessageDO message1 = messageList.get(0);
        if (message1 == null) {
            messageDO.setDialogueId(String.valueOf(SnowflakeUtils.genId()));
        } else {
            messageDO.setDialogueId(message1.getDialogueId());
        }
        int insert = messageDao.insert(messageDO);
        return Result.succeed(insert);
    }
}
