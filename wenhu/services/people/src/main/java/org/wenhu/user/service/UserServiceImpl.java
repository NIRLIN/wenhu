package org.wenhu.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.dao.UserDao;
import org.wenhu.pojo.DO.UserDO;
import org.wenhu.pojo.DTO.UserDTO;
import org.wenhu.util.Result;
import org.wenhu.util.ResultCode;
import org.wenhu.util.SnowflakeUtils;
import org.wenhu.util.TencentSendSms;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result<String> userRegister(UserDTO userDTO) {
        String message = null;
        String code = null;
        Result<String> stringResult = checkPhoneExist(userDTO.getPhoneNumber());
        if (stringResult.getCode().equals(ResultCode.USER_ERROR_A0154.getCode())){
            message=ResultCode.USER_ERROR_A0100.getMessage();
            code=ResultCode.USER_ERROR_A0100.getCode();
            return Result.succeed(code, message);
        }
        UserDO userDO = new UserDO();
        userDO.setId(String.valueOf(SnowflakeUtils.genId()));
        userDO.setUsername(userDTO.getUsername());
        userDO.setPhoneNumber(userDTO.getPhoneNumber());
        userDO.setPassword(userDTO.getPassword());
        userDO.setEmail("这个人没填邮箱哦~");
        userDO.setResume("这个人没填个性签名哦~");
        userDO.setHeadImage("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        userDO.setIsBanned(0);
        userDO.setDeadlineDate(LocalDateTime.now());
        userDO.setCreateTime(LocalDateTime.now());
        userDO.setUpdateTime(LocalDateTime.now());
        userDO.setIsDeleted(0);
        int insert = userDao.insert(userDO);
        if (insert == 1) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
        }else if (insert == 0) {
            code = ResultCode.USER_ERROR_A0100.getCode();
            message = ResultCode.USER_ERROR_A0100.getMessage();
        } else {
            code = ResultCode.UNKNOWN_ERROR.getCode();
            message = ResultCode.UNKNOWN_ERROR.getMessage();
        }
        return Result.succeed(code, message);
    }

    @Override
    public Result<String> checkPhoneExist(String phoneNumber) {
        String message = null;
        String code = null;
        HashMap<String, Object> stringHashMap = new HashMap<>(1);
        stringHashMap.put("phone_number", phoneNumber);
        List<UserDO> userDO = userDao.selectByMap(stringHashMap);
        if (userDO.size() >= 1) {
            message = ResultCode.USER_ERROR_A0154.getMessage();
            code = ResultCode.USER_ERROR_A0154.getCode();
        }
        if (userDO.size() == 0) {
            message = ResultCode.SUCCESS.getMessage();
            code = ResultCode.SUCCESS.getCode();
        }
        return Result.succeed(code, message);
    }

    @Override
    public String getPhoneVerifyCode(String phoneNumber,String verifyCode) {
        String s = TencentSendSms.sendSmsUtil(phoneNumber, verifyCode);
        return ResultCode.SUCCESS.getCode();
    }

    @Override
    public Result<String> loginByPassword(UserDTO userDTO) {
        return null;
    }

    @Override
    public Result<String> loginByVerifyCode(UserDTO userDTO) {
        return null;
    }

    @Override
    public Result<String> changePassword(UserDTO userDTO) {
        return null;
    }

    @Override
    public Result<String> getUserHomepage(UserDTO userDTO) {
        return null;
    }

    @Override
    public Result<String> getUserInfo(UserDTO userDTO) {
        return null;
    }
}
