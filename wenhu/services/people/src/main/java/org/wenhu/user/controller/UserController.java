package org.wenhu.user.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wenhu.pojo.DTO.UserDTO;
import org.wenhu.user.service.UserServiceImpl;
import org.wenhu.util.Result;
import org.wenhu.util.ResultCode;

import java.util.List;
import java.util.Map;

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

    @GetMapping(name = "test", value = "test")
    String test() {
        log.debug("test==>test");
        return "test";
    }

    @RequestMapping(name = "userRegister", value = "userRegister", method = RequestMethod.POST)
    Result<String> userRegister(@RequestBody UserDTO userDTO) {
        Result<String> register = userService.userRegister(userDTO);
        System.out.println("userDTO"+userDTO);
        System.out.println("register"+register);
        return register;
    }

    @PostMapping(name = "checkPhoneExistAndSendSms", value = "checkPhoneExistAndSendSms")
    Result<String> checkPhoneExistAndSendSms(@RequestBody Map<String,Object> checkAndSend) {
        String phoneNumber= (String) checkAndSend.get("phoneNumber");
        String verifyCode= (String) checkAndSend.get("verifyCode");
        Result<String> stringResult = userService.checkPhoneExist(phoneNumber);
        String succeedCode= ResultCode.SUCCESS.getCode();
        if (succeedCode.equals(stringResult.getCode())){
            String code = getPhoneVerifyCode(phoneNumber, verifyCode);
            if (code.equals(succeedCode)){
                return Result.succeed("获取验证码成功");
            }else {
                return Result.failed("获取验证码失败");
            }
        }
        return stringResult;
    }

    String getPhoneVerifyCode(String phoneNumber,String verifyCode) {
        return userService.getPhoneVerifyCode(phoneNumber, verifyCode);
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
