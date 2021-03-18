package org.wenhu.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.dao.UserDao;
import org.wenhu.pojo.DO.UserDO;
import org.wenhu.pojo.DTO.UserDTO;
import org.wenhu.util.ResultCode;

import java.time.LocalDateTime;

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
    public String register(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        userDO.setId(userDTO.getId());
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
        String message = "";
        if (insert == 1) {
            message = ResultCode.SUCCESS.getMessage();
        }
        if (insert == 0) {
            message = ResultCode.USER_ERROR_A0100.getMessage();
        } else {
            message = ResultCode.UNKNOWN_ERROR.getMessage();
        }
        return message;
    }

    @Override
    public String checkPhoneExist(UserDTO userDTO) {
        return null;
    }

    @Override
    public String getPhoneVerifyCode(UserDTO userDTO) {
        return null;
    }

    @Override
    public String loginByPassword(UserDTO userDTO) {
        return null;
    }

    @Override
    public String loginByVerifyCode(UserDTO userDTO) {
        return null;
    }

    @Override
    public String changePassword(UserDTO userDTO) {
        return null;
    }

    @Override
    public String getUserHomepage(UserDTO userDTO) {
        return null;
    }

    @Override
    public String getUserInfo(UserDTO userDTO) {
        return null;
    }
}
