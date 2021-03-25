package org.wenhu.people.follow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.FollowUserDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.database.dao.FollowUserDao;
import org.wenhu.people.follow.service.FollowService;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowUserDao followUserDao;

    @Override
    public List<FollowUserDO> listUserFollowByUserId(UserDTO userDTO) {
        //查询用户关注的人
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("follower_id", userDTO.getId());
        return followUserDao.selectByMap(hashMap);
    }

    @Override
    public List<FollowUserDO> listUserFansByUserId(UserDTO userDTO) {
        //查询用户关注的人
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("by_follower_id", userDTO.getId());
        return followUserDao.selectByMap(hashMap);
    }
}
