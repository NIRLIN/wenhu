package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.UserServiceImpl;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.database.dao.UserDao;

import java.util.HashMap;
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
    private UserServiceImpl userService;

    @Autowired
    private UserDao userDao;

    @ResponseBody
    @PostMapping("listUserNoBanned")
    public HashMap<String, Object> listUserNoBanned(@RequestBody Map<String, Object> objectMap) {
        return userService.listUserNoBanned(objectMap);
    }

    @ResponseBody
    @PostMapping("listUserIsBanned")
    public HashMap<String, Object> listUserIsBanned(@RequestBody Map<String, Object> objectMap) {
        return userService.listUserIsBanned(objectMap);
    }

    @ResponseBody
    @PostMapping("listUserNoBanedSearch")
    public HashMap<String, Object> listUserNoBanedSearch(@RequestBody Map<String, Object> objectMap) {
        return userService.listUserNoBanedSearch(objectMap);
    }

    @ResponseBody
    @PostMapping("listUserIsBanedSearch")
    public HashMap<String, Object> listUserIsBanedSearch(@RequestBody Map<String, Object> objectMap) {
        return userService.listUserIsBanedSearch(objectMap);
    }

    @ResponseBody
    @PostMapping("saveUser")
    public Boolean saveUser(@RequestBody UserDO userDO) {
        return userService.saveUser(userDO);
    }
}
