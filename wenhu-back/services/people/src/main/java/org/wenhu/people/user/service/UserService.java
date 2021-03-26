package org.wenhu.people.user.service;


import cn.hutool.json.JSONObject;
import org.wenhu.common.pojo.DTO.HomepageDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/18
 */
public interface UserService {
    Result<String> userRegister(UserDTO userDTO);

    Result<String> checkPhoneExist(String phoneNumber);

    String getPhoneVerifyCode(String phoneNumber, String verifyCode);

    Result<String> userLoginByPassword(UserDTO userDTO);

    Result<String> userLoginByPhoneVerify(UserDTO userDTO, String verifyCode);

    Result<String> changePassword(UserDTO userDTO);

    Result<String> getUserHomepage(UserDTO userDTO);

    Result<UserDTO> getUserInfo(UserDTO userDTO);

    Result<HomepageDTO> getHomepageByUserId(UserDTO userDTO);

    Result<JSONObject> getUserHomepageDataByUserId(UserDTO userDTO);


}
