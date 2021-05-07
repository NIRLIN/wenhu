package org.wenhu.people.follow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.FollowUserDO;
import org.wenhu.common.pojo.DTO.FollowUserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.database.dao.FollowUserDao;
import org.wenhu.people.follow.service.FollowService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowUserDao followUserDao;

    @GlobalTransactional
    @Override
    public HashMap<String, Object> listFollowByUserId(String userId) {
        //查询用户关注的人
        List<FollowUserDTO> follows = followUserDao.listFollow(userId);
        //查询关注用户的人
        List<FollowUserDTO> fans = followUserDao.listFans(userId);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("follows", follows);
        hashMap.put("fans", fans);
        return hashMap;
    }

    @GlobalTransactional
    @Override
    public Result<HashMap<String, Object>> followUserOperation(String userId, String byFollowerId) {
        QueryWrapper<FollowUserDO> followUserQueryWrapper = new QueryWrapper<>();
        followUserQueryWrapper
                .eq("follower_id", userId)
                .eq("by_follower_id", byFollowerId);
        FollowUserDO followUserDO = followUserDao.selectOne(followUserQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(1);

        if (followUserDO != null) {
            if (followUserDO.getIsDeleted() == 0) {
                followUserDO.setIsDeleted(1);
                //删除关注时否认
                hashMap.put("followResult", false);
            } else if (followUserDO.getIsDeleted() == 1) {
                followUserDO.setIsDeleted(0);
                hashMap.put("followResult", true);
            }
            followUserDO.setUpdateTime(LocalDateTime.now());
            followUserDao.updateById(followUserDO);
        } else {
            followUserDO = new FollowUserDO()
                    .setId(String.valueOf(SnowflakeUtils.genId()))
                    .setFollowerId(userId)
                    .setByFollowerId(byFollowerId)
                    .setCreateTime(LocalDateTime.now())
                    .setUpdateTime(LocalDateTime.now())
                    .setIsDeleted(0);
            int insert = followUserDao.insert(followUserDO);
            if (insert == 1) {
                hashMap.put("followResult", true);
            } else {
                hashMap.put("followResult", false);
            }
        }
        return Result.succeed(hashMap);
    }

    @GlobalTransactional
    @Override
    public Result<HashMap<String, Object>> getUserFollow(String userId, String byFollowerId) {
        QueryWrapper<FollowUserDO> followUserQueryWrapper = new QueryWrapper<>();
        followUserQueryWrapper
                .eq("follower_id", userId)
                .eq("by_follower_id", byFollowerId)
                .eq("is_deleted", 0);
        FollowUserDO followUserDO = followUserDao.selectOne(followUserQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(1);

        if (followUserDO != null) {
            hashMap.put("followResult", true);
        } else {
            hashMap.put("followResult", false);
        }
        return Result.succeed(hashMap);
    }

    @GlobalTransactional
    @Override
    public Result<HashMap<String, Object>> listCommonFollow(String homeUserid, String loginUserId) {
        List<FollowUserDTO> otherFollowList = followUserDao.listFollow(homeUserid);
        List<FollowUserDTO> myFollowList = followUserDao.listFollow(loginUserId);

        //交集查询
        List<FollowUserDTO> commonFollowList = myFollowList.stream()
                .filter(item -> otherFollowList.stream()
                        .map(FollowUserDTO::getByFollowerId).collect(Collectors.toList()).contains(item.getByFollowerId()))
                .collect(Collectors.toList());
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("commonFollow", commonFollowList);
        return Result.succeed(hashMap);
    }
}
