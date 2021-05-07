package org.wenhu.people.user.service;


import org.springframework.web.multipart.MultipartFile;
import org.wenhu.common.pojo.DO.HomepageDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.common.pojo.DTO.AnswerDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/18
 */
public interface UserService {
    /**
     * 注册
     *
     * @param userDO userDO
     * @return 操作结果
     */
    Result<String> userRegister(UserDO userDO);

    /**
     * 检查手机是否已注册
     *
     * @param phoneNumber 手机号
     * @return 操作结果
     */
    Result<String> checkPhoneExist(String phoneNumber);

    /**
     * 获取验证码
     *
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     * @return 操作结果
     */
    String getPhoneVerifyCode(String phoneNumber, String verifyCode);

    /**
     * 通过密码登录
     *
     * @param userDTO 用户信息
     * @return 操作结果
     */
    Result<String> userLoginByPassword(UserDTO userDTO);

    /**
     * 验证码登录
     *
     * @param userDTO    用户信息
     * @param verifyCode 验证码
     * @return 操作结果
     */
    Result<String> userLoginByPhoneVerify(UserDTO userDTO, String verifyCode);

    /**
     * 修改密码
     *
     * @param userId      用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 操作结果
     */
    Result<String> changePassword(String userId, String oldPassword, String newPassword);

    /**
     * 获取用户信息
     *
     * @param userDTO 用户信息
     * @return 操作结果
     */
    Result<UserDTO> getUserInfo(UserDTO userDTO);

    /**
     * 获取用户主页
     *
     * @param userDTO 用户信息
     * @return 操作结果
     */
    Result<HomepageDO> getHomepageByUserId(UserDTO userDTO);

    /**
     * 回答列表
     *
     * @param userId 用户id
     * @param type   type
     * @return 操作结果
     */
    Result<List<AnswerDTO>> listAnswerByUserId(String userId, String type);

    /**
     * 问题列表
     *
     * @param userId 用户id
     * @return 操作结果
     */
    Result<List<QuestionDO>> listQuestionByUserId(String userId);

    /**
     * 收藏列表
     *
     * @param userId 用户id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> listCollectByUserId(String userId);

    /**
     * 关注列表
     *
     * @param userId 用户id
     * @return 操作结果
     */
    Result<HashMap<String, Object>> listFollowByUserId(String userId);

    /**
     * 检查旧手机
     *
     * @param userId      用户id
     * @param code        验证码
     * @param phoneNumber 手机号
     * @return 操作结果
     */
    Result<UserDO> checkOldPhoneNumber(String userId, String code, String phoneNumber);

    /**
     * 验证新手机
     *
     * @param userId      用户id
     * @param code        验证码
     * @param phoneNumber 手机号
     * @return 操作结果
     */
    Result<UserDO> checkNewPhoneNumber(String userId, String code, String phoneNumber);

    /**
     * 修改为新手机
     *
     * @param userId      用户id
     * @param phoneNumber 手机号
     * @return 操作结果
     */
    Result<UserDO> changeNewPhoneNumber(String userId, String phoneNumber);

    /**
     * 修改主页
     *
     * @param homepageDO 主页数据
     * @return 操作结果
     */
    Result<HomepageDO> saveChangeHomepage(HomepageDO homepageDO);

    /**
     * 获取用户签名
     *
     * @param userId 用户id
     * @return 操作结果
     */
    Result<String> getResumeByUserId(String userId);

    /**
     * 保存签名
     *
     * @param userId 用户id
     * @param resume 签名
     * @return 操作结果
     */
    Result<String> saveResumeByUserId(String userId, String resume);

    /**
     * 获取头像
     *
     * @param userId 用户id
     * @return 操作结果
     */
    Result<String> getHeadImageByUserId(String userId);

    /**
     * 保存头像
     *
     * @param image  头像
     * @param userId 用户id
     * @return 操作结果
     */
    Result<String> saveHeadImageByUserId(MultipartFile image, String userId);

    /**
     * 获取用户名
     *
     * @param userId 用户id
     * @return 操作结果
     */
    Result<String> getUsernameByUserId(String userId);

    /**
     * 保存用户名
     *
     * @param userId   用户id
     * @param username 用户名
     * @return 操作结果
     */
    Result<String> saveUsernameByUserId(String userId, String username);
}
