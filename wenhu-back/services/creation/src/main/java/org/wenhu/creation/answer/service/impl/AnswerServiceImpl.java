package org.wenhu.creation.answer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.creation.answer.service.AnswerService;
import org.wenhu.database.dao.AnswerDao;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Override
    public List<AnswerDO> listAnswerByUserId(UserDTO userDTO) {
        //获取回答
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("user_id", userDTO.getId());
        return answerDao.selectByMap(hashMap);
    }
}
