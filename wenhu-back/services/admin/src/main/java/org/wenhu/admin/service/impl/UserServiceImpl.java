package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.UserService;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.database.dao.UserDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDO userLogin(String phoneNumber, String password) {
        //手机号或密码为空不允许登录验证
        if (phoneNumber == null || password == null) {
            return null;
        }
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("phone_number", phoneNumber)
                .eq("password", password);
        return userDao.selectOne(queryWrapper);
    }


    @Override
    public String changePassword(String userId, String oldPassword, String oneNewPassword) {
        if (userId == null) {
            return "未登录哦~";
        }
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", userId)
                .eq("password", oldPassword);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO == null) {
            return "密码错误";
        }
        userDO.setPassword(oneNewPassword);
        userDO.setUpdateTime(LocalDateTime.now());
        int i = userDao.updateById(userDO);
        if (i == 1) {
            return "修改成功,请重新登录~";
        } else {
            return "修改失败";
        }
    }

    @Override
    public HashMap<String, Object> listUserNoBanned(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        //条件构造
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper<>();
        //未封禁用户
        userQueryWrapper
                .lt("deadline_date", LocalDateTime.now())
                .orderByDesc("update_time");
        //分页查询
        List<UserDO> listUserNoBanned = userListByPage(page, userQueryWrapper);
        //查询总数
        Integer listUserNoBannedCount = userListCount(userQueryWrapper);

        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listUserNoBanned", listUserNoBanned);
        hashMap.put("listUserNoBannedCount", listUserNoBannedCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> listUserIsBanned(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        //条件构造
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper<>();
        //已封禁用户
        userQueryWrapper
                .gt("deadline_date", LocalDateTime.now())
                .orderByDesc("update_time");
        //分页查询
        List<UserDO> listUserIsBanned = userListByPage(page, userQueryWrapper);
        //查询总数
        Integer listUserIsBannedCount = userListCount(userQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listUserIsBanned", listUserIsBanned);
        hashMap.put("listUserIsBannedCount", listUserIsBannedCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> listUserNoBanedSearch(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        //条件构造
        QueryWrapper<UserDO> userQueryWrapper = searchInitQueryWrapper(objectMap);
        userQueryWrapper
                .lt("deadline_date", LocalDateTime.now());
        //分页查询
        List<UserDO> listUserSearchNoBanned = userListByPage(page, userQueryWrapper);
        //查询总数
        Integer listUserSearchNoBannedCount = userListCount(userQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listUserSearchNoBanned", listUserSearchNoBanned);
        hashMap.put("listUserSearchNoBannedCount", listUserSearchNoBannedCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> listUserIsBanedSearch(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        //构建条件
        QueryWrapper<UserDO> userQueryWrapper = searchInitQueryWrapper(objectMap);
        userQueryWrapper
                .gt("deadline_date", LocalDateTime.now());
        //分页查询
        List<UserDO> listUserSearchIsBanned = userListByPage(page, userQueryWrapper);
        //查询总数
        Integer listUserSearchIsBannedCount = userListCount(userQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listUserSearchIsBanned", listUserSearchIsBanned);
        hashMap.put("listUserSearchIsBannedCount", listUserSearchIsBannedCount);
        return hashMap;
    }

    private QueryWrapper<UserDO> searchInitQueryWrapper(Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String username = (String) objectMap.get("username");
        String phoneNumber = (String) objectMap.get("phoneNumber");
        String resume = (String) objectMap.get("resume");
        //条件构造
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .orderByDesc("update_time")
                .like(userId != null, "id", userId)
                .like(username != null, "username", username)
                .like(phoneNumber != null, "phone_number", phoneNumber)
                .like(resume != null, "resume", resume);
        return userQueryWrapper;
    }

    private List<UserDO> userListByPage(Integer page, QueryWrapper<UserDO> queryWrapper) {
        //分页查询
        Page<UserDO> doPage = new Page<>(page, 10);
        Page<UserDO> selectPage = userDao.selectPage(doPage, queryWrapper);
        return selectPage.getRecords();
    }

    private Integer userListCount(QueryWrapper<UserDO> queryWrapper) {
        //查询总数
        return userDao.selectCount(queryWrapper);
    }

    @Override
    public Boolean updateUser(UserDO userDO) {
        //修改时间
        userDO.setUpdateTime(LocalDateTime.now());
        int i = userDao.updateById(userDO);
        return i == 1;
    }


}
