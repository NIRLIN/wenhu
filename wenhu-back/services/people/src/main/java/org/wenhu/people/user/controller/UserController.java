package org.wenhu.people.user.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.HomepageDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.pojo.VO.AnswerVO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.people.user.service.impl.UserServiceImpl;

import java.util.HashMap;
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
        System.out.println("userDTO" + userDTO);
        System.out.println("register" + register);
        return register;
    }

    @PostMapping(name = "checkPhoneExistAndSendSms", value = "checkPhoneExistAndSendSms")
    Result<String> checkPhoneExistAndSendSms(@RequestBody Map<String, Object> checkAndSend) {
        String phoneNumber = (String) checkAndSend.get("phoneNumber");
        String verifyCode = (String) checkAndSend.get("verifyCode");
        Result<String> stringResult = userService.checkPhoneExist(phoneNumber);
        String succeedCode = ResultCode.SUCCESS.getCode();
        if (succeedCode.equals(stringResult.getCode())) {
            String code = getPhoneVerifyCode(phoneNumber, verifyCode);
            if (code.equals(succeedCode)) {
                return Result.succeed("获取验证码成功");
            } else {
                return Result.failed("获取验证码失败");
            }
        }
        return stringResult;
    }

    @PostMapping(name = "sendVerifyCode", value = "sendVerifyCode")
    String sendVerifyCode(String phoneNumber, String verifyCode) {
        return userService.getPhoneVerifyCode(phoneNumber, verifyCode);
    }

    String getPhoneVerifyCode(String phoneNumber, String verifyCode) {
        return userService.getPhoneVerifyCode(phoneNumber, verifyCode);
    }

    @PostMapping(name = "userLoginByPassword", value = "userLoginByPassword")
    Result<String> userLoginByPassword(@RequestBody Map<String, Object> objectMap) {
        String phoneNumber = (String) objectMap.get("phoneNumber");
        String password = (String) objectMap.get("password");
        UserDTO userDTO = new UserDTO();
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setPassword(password);
        return userService.userLoginByPassword(userDTO);
    }

    @PostMapping(name = "userLoginByPhoneVerify", value = "userLoginByPhoneVerify")
    Result<String> userLoginByPhoneVerify(@RequestBody Map<String, Object> objectMap) {
        String phoneNumber = (String) objectMap.get("phoneNumber");
        String verifyCode = (String) objectMap.get("verifyCode");
        UserDTO userDTO = new UserDTO();
        userDTO.setPhoneNumber(phoneNumber);
        System.out.println(phoneNumber + verifyCode);
        return userService.userLoginByPhoneVerify(userDTO, verifyCode);
    }


    @PostMapping(name = "getUserInfo", value = "getUserInfo")
    Result<UserDTO> getUserInfo(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        Result<UserDTO> userInfo = userService.getUserInfo(userDTO);
        System.out.println(userInfo);
        return userInfo;
    }

    Result<String> changePassword() {
        return null;
    }

    @PostMapping(name = "getHomepageByUserId", value = "getHomepageByUserId")
    Result<HomepageDTO> getHomepageByUserId(@RequestBody Map<String, Object> objectMap) {
        String id = (String) objectMap.get("id");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        return userService.getHomepageByUserId(userDTO);
    }



    @PostMapping(name = "listAnswerByUserId", value = "listAnswerByUserId")
    public Result<List<AnswerVO>> listAnswerByUserId(@RequestBody Map<String, Object> objectMap) {
        //用户id
        String userId = (String) objectMap.get("id");
        //获取方式
        String type = (String) objectMap.get("type");
        return userService.listAnswerByUserId(userId,type);
    }



    @PostMapping(name = "listArticleByUserId", value = "listArticleByUserId")
    public Result<List<AnswerVO>> listArticleByUserId(@RequestBody Map<String, Object> objectMap) {
        //用户id
        String userId = (String) objectMap.get("id");
        //获取方式
        String type = (String) objectMap.get("type");
        return userService.listArticleByUserId(userId,type);
    }

    @PostMapping(name = "listQuestionByUserId", value = "listQuestionByUserId")
    public Result<List<QuestionDO>>  listQuestionByUserId(@RequestBody Map<String, Object> objectMap) {
        //用户id
        String userId = (String) objectMap.get("id");
        return userService.listQuestionByUserId(userId);
    }

    @PostMapping(name = "listCollectByUserId", value = "listCollectByUserId")
    public Result<HashMap<String, Object>>  listCollectByUserId(@RequestBody Map<String, Object> objectMap) {
        //用户id
        String userId = (String) objectMap.get("id");
        return userService.listCollectByUserId(userId);
    }
    @PostMapping(name = "listFollowByUserId", value = "listFollowByUserId")
    public Result<HashMap<String, Object>>  listFollowByUserId(@RequestBody Map<String, Object> objectMap) {
        //用户id
        String userId = (String) objectMap.get("id");
        return userService.listFollowByUserId(userId);
    }

}
