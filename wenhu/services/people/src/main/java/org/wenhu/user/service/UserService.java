package org.wenhu.user.service;

import org.wenhu.pojo.DTO.UserDTO;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/18
 */
public interface UserService {
    String register(UserDTO userDTO);

    String checkPhoneExist(UserDTO userDTO);

    String getPhoneVerifyCode(UserDTO userDTO);

    String loginByPassword(UserDTO userDTO);

    String loginByVerifyCode(UserDTO userDTO);

    String changePassword(UserDTO userDTO);

    String getUserHomepage(UserDTO userDTO);

    String getUserInfo(UserDTO userDTO);
}
