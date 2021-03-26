package org.wenhu.people.user.service.impl;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.HomepageDO;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.common.pojo.DTO.HomepageDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;
import org.wenhu.common.util.ResultCode;
import org.wenhu.common.util.SnowflakeUtils;
import org.wenhu.common.util.TencentSendSms;
import org.wenhu.database.dao.HomepageDao;
import org.wenhu.database.dao.UserDao;
import org.wenhu.feign.feign.CreationFeignClient;
import org.wenhu.feign.feign.PeopleFeignClient;
import org.wenhu.people.user.service.UserService;

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
    @Autowired
    private HomepageDao homepageDao;
    @Autowired
    private CreationFeignClient creationFeignClient;
    @Autowired
    private PeopleFeignClient peopleFeignClient;

    @Override
    public Result<String> userRegister(UserDTO userDTO) {
        String message = null;
        String code;
        Result<String> stringResult = checkPhoneExist(userDTO.getPhoneNumber());
        //判断手机号是否已绑定，已绑定时不可注册
        if (stringResult.getCode().equals(ResultCode.USER_ERROR_A0154.getCode())) {
            message = ResultCode.USER_ERROR_A0100.getMessage();
            code = ResultCode.USER_ERROR_A0100.getCode();
            return Result.succeed(code, message);
        }
        //数据补全
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
        //插入成功返回成功
        if (insert == 1) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
        } else if (insert == 0) {
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
        //条件构造器
        HashMap<String, Object> stringHashMap = new HashMap<>(1);
        stringHashMap.put("phone_number", phoneNumber);
        List<UserDO> userDO = userDao.selectByMap(stringHashMap);
        //返回结果-手机号已绑定、成功
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
    public String getPhoneVerifyCode(String phoneNumber, String verifyCode) {
        //发送短信
        String s = TencentSendSms.sendSmsUtil(phoneNumber, verifyCode);
        return ResultCode.SUCCESS.getCode();
    }

    @Override
    public Result<String> userLoginByPassword(UserDTO userDTO) {
        Result<String> stringResult = checkPhoneExist(userDTO.getPhoneNumber());
        String code = null;
        String message = null;
        String data = null;
        System.out.println("userDTO" + userDTO);
        System.out.println("stringResult" + stringResult);
        //判断手机号是否已绑定，未绑定时不可验证登录
        if (ResultCode.USER_ERROR_A0154.getCode().equals(stringResult.getCode())) {
            HashMap<String, Object> stringHashMap = new HashMap<>(1);
            stringHashMap.put("phone_number", userDTO.getPhoneNumber());
            List<UserDO> userDO = userDao.selectByMap(stringHashMap);
            System.out.println("userDO" + userDO);
            if (userDO.size() >= 1) {
                for (UserDO aDo : userDO) {
                    //循环取出数据，验证账号密码
                    if (aDo.getPhoneNumber().equals(userDTO.getPhoneNumber()) && aDo.getPassword().equals(userDTO.getPassword())) {
                        code = ResultCode.SUCCESS.getCode();
                        message = ResultCode.SUCCESS.getMessage();
                        //构造id数据进行返回
                        data = "{\"id\":\"" + aDo.getId() + "\"}";
                        //避免数据错误
                        break;
                    }
                    if (!aDo.getPassword().equals(userDTO.getPassword())) {
                        code = ResultCode.USER_ERROR_A0210.getCode();
                        message = ResultCode.USER_ERROR_A0210.getMessage();
                    }
                }
            } else {
                //避免验证不存在数据库中的手机号
                code = ResultCode.USER_ERROR_A0201.getCode();
                message = ResultCode.USER_ERROR_A0201.getMessage();
            }
        } else {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();
        }
        // System.out.println(Result.succeed(code, message, data));
        return Result.succeed(code, message, data);
    }

    @Override
    public Result<String> userLoginByPhoneVerify(UserDTO userDTO, String verifyCode) {
        Result<String> stringResult = checkPhoneExist(userDTO.getPhoneNumber());
        String code = null;
        String message = null;
        String data = null;
        //判断手机号是否存在
        if (ResultCode.USER_ERROR_A0154.getCode().equals(stringResult.getCode())) {
            HashMap<String, Object> stringHashMap = new HashMap<>(1);
            stringHashMap.put("phone_number", userDTO.getPhoneNumber());
            List<UserDO> userDO = userDao.selectByMap(stringHashMap);
            if (userDO != null) {
                code = ResultCode.SUCCESS.getCode();
                message = ResultCode.SUCCESS.getMessage();
                for (UserDO aDo : userDO) {
                    if (aDo != null) {
                        getPhoneVerifyCode(userDTO.getPhoneNumber(), verifyCode);
                        data = "{\"id\":\"" + aDo.getId() + "\"}";
                    }
                }
            }
        } else {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();

        }
        return Result.succeed(code, message, data);
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
    public Result<UserDTO> getUserInfo(UserDTO userDTO) {
        String code = null;
        String message = null;
        if (userDTO.getId() == null || "".equals(userDTO.getId())) {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();
        }
        UserDO userDO = userDao.selectById(userDTO.getId());
        if (userDO == null) {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();
        } else {
            userDTO.setUsername(userDO.getUsername());
            userDTO.setResume(userDO.getResume());
            userDTO.setHeadImage(userDO.getHeadImage());
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
        }
        return Result.succeed(code, message, userDTO);
    }

    @Override
    public Result<HomepageDTO> getHomepageByUserId(UserDTO userDTO) {
        String code = null;
        String message = null;
        HomepageDTO data = null;
        //数据不存在时默认homepage全部展示
        if (userDTO.getId() == null || "".equals(userDTO.getId())) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
        } else {
            HomepageDO homepageDO = homepageDao.selectById(userDTO.getId());
            if (homepageDO != null) {
                code = ResultCode.SUCCESS.getCode();
                message = ResultCode.SUCCESS.getMessage();
                HomepageDTO homepageDTO = new HomepageDTO();
                homepageDTO.setId(homepageDO.getId());
                homepageDTO.setAnswer(homepageDO.getAnswer());
                homepageDTO.setQuestion(homepageDO.getQuestion());
                homepageDTO.setArticle(homepageDO.getArticle());
                homepageDTO.setCollect(homepageDO.getCollect());
                homepageDTO.setFollow(homepageDO.getFollow());
                data = homepageDTO;
            } else {
                //数据不存在时默认homepage全部展示
                code = ResultCode.SUCCESS.getCode();
                message = ResultCode.SUCCESS.getMessage();
            }

        }
        return Result.succeed(data);
    }

    @Override
    public Result<JSONObject> getUserHomepageDataByUserId(UserDTO userDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("answer",creationFeignClient.listAnswerByUserId(userDTO));
        jsonObject.putOpt("article",creationFeignClient.listArticleByUserId(userDTO));
        jsonObject.putOpt("question",creationFeignClient.listQuestionByUserId(userDTO));
        jsonObject.putOpt("favorite",peopleFeignClient.listFavoriteByUserId(userDTO));
        jsonObject.putOpt("userFollow",peopleFeignClient.listUserFollowByUserId(userDTO));
        jsonObject.putOpt("userFans",peopleFeignClient.listUserFansByUserId(userDTO));
        System.out.println("jsonObject"+jsonObject);
        return Result.succeed(jsonObject);
    }

}
