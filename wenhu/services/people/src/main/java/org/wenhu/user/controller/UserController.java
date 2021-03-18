package org.wenhu.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.pojo.DTO.UserDTO;
import org.wenhu.user.service.UserServiceImpl;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Slf4j
@RestController
@RequestMapping(name = "user", value = "user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(name = "register", value = "register", method = RequestMethod.POST)
    String register(@RequestBody UserDTO userDTO) {
        String register = userService.register(userDTO);
        log.debug("userDTO==>" + userDTO);
        log.debug("register==>" + register);
        return "";
    }

    String checkPhoneExist() {
        return "";
    }

    String getPhoneVerifyCode() {
        return "";
    }

    String loginByPassword() {
        return "";
    }

    String loginByVerifyCode() {
        return "";
    }

    String changePassword() {
        return "";
    }

    String getUserHomepage() {
        return "";
    }

    String getUserInfo() {
        return "";
    }
}
