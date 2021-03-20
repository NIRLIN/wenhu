package org.wenhu.user.service;

import org.wenhu.pojo.DTO.UserDTO;
import org.wenhu.util.Result;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/18
 */
public interface UserService {
    Result<String> userRegister(UserDTO userDTO);

    Result<String> checkPhoneExist(String phoneNumber);

    String getPhoneVerifyCode(String phoneNumber,String verifyCode);

    Result<String> loginByPassword(UserDTO userDTO);

    Result<String> loginByVerifyCode(UserDTO userDTO);

    Result<String> changePassword(UserDTO userDTO);

    Result<String> getUserHomepage(UserDTO userDTO);

    Result<String> getUserInfo(UserDTO userDTO);
}
