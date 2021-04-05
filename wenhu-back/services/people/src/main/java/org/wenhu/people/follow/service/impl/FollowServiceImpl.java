package org.wenhu.people.follow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DTO.FollowUserDTO;
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
    public HashMap<String, Object> listFollowByUserId(String userId) {
        //查询用户关注的人
        List<FollowUserDTO> follows = followUserDao.listFollow(userId);
        //查询关注用户的人
        List<FollowUserDTO> fans = followUserDao.listFans(userId);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("follows", follows);
        hashMap.put("fans", fans);
        return hashMap;
    }
}
