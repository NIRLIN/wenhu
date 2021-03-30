package org.wenhu.people.user.service;


import cn.hutool.json.JSONObject;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.HomepageDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.pojo.VO.AnswerVO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.List;

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


    Result<UserDTO> getUserInfo(UserDTO userDTO);

    Result<HomepageDTO> getHomepageByUserId(UserDTO userDTO);

    Result<List<AnswerVO>> listAnswerByUserId(String userId, String type);

    Result<List<AnswerVO>> listArticleByUserId(String userId, String type);

    Result<List<QuestionDO>>  listQuestionByUserId(String userId);

    Result<HashMap<String, Object>> listCollectByUserId(String userId);

    Result<HashMap<String, Object>> listFollowByUserId(String userId);
}
