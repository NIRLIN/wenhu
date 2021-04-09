package org.wenhu.admin.controller;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.database.dao.UserDao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/8
 */
@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("userList.html")
    public String userList(Model model) {
        QueryWrapper<UserDO> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("is_banned", 0);
        List<UserDO> userDoList = userDao.selectList(userQueryWrapper);

        JSONArray jsonArray = new JSONArray();
        userDoList.stream().map(jsonArray::add).collect(Collectors.toList());

//        JSONArray jsonArrays = userDoList.stream().map(t -> new JSONArray().put(t));
        model.addAttribute("userDoList", jsonArray);
        model.addAttribute("userDoObject", userDoList);

        System.out.println(jsonArray);
        return "view/userList";
    }
}
