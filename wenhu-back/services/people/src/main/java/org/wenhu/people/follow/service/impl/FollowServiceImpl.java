package org.wenhu.people.follow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public HashMap<String, Object> listFollowByUserId(String userId) {
        //查询用户关注的人
        QueryWrapper<FollowUserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId!=null,"follower_id",userId);
        List<FollowUserDO> followUserDoS = followUserDao.selectList(queryWrapper);

        //查询关注用户的人
        QueryWrapper<FollowUserDO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq(userId != null, "by_follower_id", userId);
        List<FollowUserDO> followUserDoS1 = followUserDao.selectList(queryWrapper1);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("followerId",followUserDoS);
        hashMap.put("byFollowerId",followUserDoS1);
        return hashMap;
    }
}
