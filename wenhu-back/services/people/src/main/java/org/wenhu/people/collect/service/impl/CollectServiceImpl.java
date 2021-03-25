package org.wenhu.people.collect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.CollectDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.database.dao.CollectDao;
import org.wenhu.people.collect.service.CollectService;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;

    @Override
    public List<CollectDO> listFavoriteByUserId(UserDTO userDTO) {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("user_id", userDTO.getId());
        return collectDao.selectByMap(hashMap);
    }
}
