package org.wenhu.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.database.dao.UserDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/8
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @ResponseBody
    @PostMapping("listUser")
    public HashMap<String, Object> userList(@RequestBody Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        //构造分页
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .lt("deadline_date", LocalDateTime.now())
                .orderByDesc("update_time");
        Page<UserDO> doPage = new Page<>(page, 10);
        //分页查询
        Page<UserDO> selectPage = userDao.selectPage(doPage, userQueryWrapper);
        //查询总数
        Integer listUserCount = userDao.selectCount(userQueryWrapper);
        List<UserDO> listUser = selectPage.getRecords();
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listUser", listUser);
        hashMap.put("listUserCount", listUserCount);
        return hashMap;
    }

    @ResponseBody
    @PostMapping("saveUser")
    public Boolean saveUser(@RequestBody UserDO userDO) {
        userDO.setUpdateTime(LocalDateTime.now());
        int i = userDao.updateById(userDO);
        return i == 1;
    }
}
