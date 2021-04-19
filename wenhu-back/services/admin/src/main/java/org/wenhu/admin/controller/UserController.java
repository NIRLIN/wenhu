package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wenhu.admin.service.impl.UserServiceImpl;
import org.wenhu.common.pojo.DO.UserDO;

import javax.servlet.http.HttpSession;
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

    @PostMapping("userLogin")
    public String userLogin(@RequestParam String phoneNumber, @RequestParam String password, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        UserDO userDO = userService.userLogin(phoneNumber, password);
        if (userDO != null) {
            httpSession.setAttribute("userId", userDO.getId());
            httpSession.setAttribute("username", userDO.getUsername());
            httpSession.removeAttribute("loginInfo");
            return "redirect:/index.html";
        } else {
            redirectAttributes.addFlashAttribute("loginInfo", "账号或密码错误");
        }
        return "redirect:/login.html";
    }

    @PostMapping("changePassword")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String oneNewPassword, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        String changePasswordSuccess = "修改成功,请重新登录~";
        String userId = (String) httpSession.getAttribute("userId");
        String changePasswordInfo = userService.changePassword(userId, oldPassword, oneNewPassword);
        redirectAttributes.addFlashAttribute("changePasswordInfo", changePasswordInfo);
        if (changePasswordSuccess.equals(changePasswordInfo)) {
            return "redirect:/login.html";
        } else {
            return "redirect:/changePassword.html";
        }
    }

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
