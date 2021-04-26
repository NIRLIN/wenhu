package org.wenhu.people.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wenhu.common.pojo.DO.HomepageDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DO.UserDO;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.pojo.DTO.HomepageDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.*;
import org.wenhu.database.dao.HomepageDao;
import org.wenhu.database.dao.UserDao;
import org.wenhu.feign.feign.CreationFeignClient;
import org.wenhu.people.collect.controller.CollectController;
import org.wenhu.people.follow.controller.FollowController;
import org.wenhu.people.user.service.UserService;

import java.io.IOException;
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
    private CollectController collectController;
    @Autowired
    private FollowController followController;


    @Override
    public Result<String> userRegister(UserDO userDO) {
        String message;
        String code;
        Result<String> stringResult = checkPhoneExist(userDO.getPhoneNumber());
        //判断手机号是否已绑定，已绑定时不可注册
        if (stringResult.getCode().equals(ResultCode.USER_ERROR_A0154.getCode())) {
            message = ResultCode.USER_ERROR_A0100.getMessage();
            code = ResultCode.USER_ERROR_A0100.getCode();
            return Result.succeed(code, message);
        }
        //数据补全
        userDO.setId(String.valueOf(SnowflakeUtils.genId()));
        userDO.setEmail("这个人没填邮箱哦~");
        userDO.setResume("这个人没填个性签名哦~");
        userDO.setHeadImage("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
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
        TencentSendSms.sendSmsUtil(phoneNumber, verifyCode);
        return ResultCode.SUCCESS.getCode();
    }

    @Override
    public Result<String> userLoginByPassword(UserDTO userDTO) {
        Result<String> stringResult = checkPhoneExist(userDTO.getPhoneNumber());
        String code = null;
        String message = null;
        String data = null;
        //判断手机号是否已绑定，未绑定时不可验证登录
        if (ResultCode.USER_ERROR_A0154.getCode().equals(stringResult.getCode())) {
            QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .eq("phone_number", userDTO.getPhoneNumber())
                    .eq("password", userDTO.getPassword());
            UserDO userDO = userDao.selectOne(queryWrapper);
            if (userDO != null) {
                if (LocalDateTime.now().compareTo(userDO.getDeadlineDate()) > 0){
                    code = ResultCode.SUCCESS.getCode();
                    message = ResultCode.SUCCESS.getMessage();
                    //构造id数据进行返回
                    data = "{\"id\":\"" + userDO.getId() + "\"}";
                }else {
                    code = ResultCode.USER_ERROR_A0202.getCode();
                    message = ResultCode.USER_ERROR_A0202.getMessage()+"截止"+userDO.getDeadlineDate();
                }

                //避免数据错误
            } else {
                code = ResultCode.USER_ERROR_A0210.getCode();
                message = ResultCode.USER_ERROR_A0210.getMessage();
            }
        } else {
            //避免验证不存在数据库中的手机号
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();
        }
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
            QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("phone_number", userDTO.getPhoneNumber());
            UserDO userDO = userDao.selectOne(queryWrapper);
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
            if (userDO != null) {
                getPhoneVerifyCode(userDTO.getPhoneNumber(), verifyCode);
                data = "{\"id\":\"" + userDO.getId() + "\"}";
            }
        } else {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();

        }
        return Result.succeed(code, message, data);
    }

    @Override
    public Result<String> changePassword(String userId, String oldPassword, String newPassword) {
        String code;
        String message;
        String data = null;
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId != null, "id", userId);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO == null) {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();
        } else {
            if (userDO.getPassword().equals(oldPassword)) {
                userDO.setPassword(newPassword);
                userDO.setUpdateTime(LocalDateTime.now());

                int i = userDao.updateById(userDO);
                if (i == 1) {
                    code = ResultCode.SUCCESS.getCode();
                    message = ResultCode.SUCCESS.getMessage();
                } else {
                    code = ResultCode.OPERATION_FAIL_D0001.getCode();
                    message = ResultCode.OPERATION_FAIL_D0001.getMessage();
                }
            } else {
                code = ResultCode.USER_ERROR_A0210.getCode();
                message = ResultCode.USER_ERROR_A0210.getMessage();
            }
        }
        return Result.succeed(code, message, data);
    }


    @Override
    public Result<UserDTO> getUserInfo(UserDTO userDTO) {
        String code;
        String message;
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
        String code;
        String message;
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
        return Result.succeed(code, message, data);
    }


    @Override
    public Result<List<AnswerArticleDTO>> listAnswerByUserId(String userId, String type) {
        return creationFeignClient.listAnswerByUserId(userId, type);
    }


    @Override
    public Result<List<AnswerArticleDTO>> listArticleByUserId(String userId, String type) {
        return creationFeignClient.listArticleByUserId(userId, type);
    }

    @Override
    public Result<List<QuestionDO>> listQuestionByUserId(String userId) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        return creationFeignClient.listQuestionByUserId(userDTO);
    }

    @Override
    public Result<HashMap<String, Object>> listCollectByUserId(String userId) {
        return collectController.listCollectByUserId(userId);
    }

    @Override
    public Result<HashMap<String, Object>> listFollowByUserId(String userId) {
        return followController.listFollowByUserId(userId);
    }

    @Override
    public Result<UserDO> checkOldPhoneNumber(String userId, String code, String phoneNumber) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(userId != null, "id", userId)
                .eq(phoneNumber != null, "phone_number", phoneNumber);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO == null) {
            return Result.succeed(ResultCode.USER_ERROR_A0201.getCode(), ResultCode.USER_ERROR_A0201.getMessage());
        }
        TencentSendSms.sendSmsUtil(phoneNumber, code);
        return Result.succeed(null);
    }

    @Override
    public Result<UserDO> checkNewPhoneNumber(String userId, String code, String phoneNumber) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(phoneNumber != null, "phone_number", phoneNumber);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO != null) {
            return Result.succeed(ResultCode.USER_ERROR_A0154.getCode(), ResultCode.USER_ERROR_A0154.getMessage());
        } else {
            TencentSendSms.sendSmsUtil(phoneNumber, code);
        }
        return Result.succeed(null);
    }

    @Override
    public Result<UserDO> changeNewPhoneNumber(String userId, String phoneNumber) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(userId != null, "id", userId);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO == null) {
            return Result.succeed(ResultCode.USER_ERROR_A0201.getCode(), ResultCode.USER_ERROR_A0201.getMessage());
        }
        userDO.setPhoneNumber(phoneNumber);
        int i = userDao.updateById(userDO);
        if (i == 1) {
            return Result.succeed(null);
        } else {
            return Result.succeed(ResultCode.OPERATION_FAIL_D0001.getCode(), ResultCode.OPERATION_FAIL_D0001.getMessage());
        }
    }

    @Override
    public Result<HomepageDTO> saveChangeHomepage(HomepageDTO homepageDTO) {
        QueryWrapper<HomepageDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(homepageDTO.getId() != null, "id", homepageDTO.getId());
        HomepageDO homepageDoDao = homepageDao.selectOne(queryWrapper);
        HomepageDO homepageDO = new HomepageDO();
        BeanUtils.copyProperties(homepageDTO, homepageDO);
        homepageDO.setUpdateTime(LocalDateTime.now());
        if (homepageDoDao == null) {
            homepageDO.setCreateTime(LocalDateTime.now());
            homepageDao.insert(homepageDO);
        }
        if (homepageDoDao != null) {
            homepageDao.updateById(homepageDO);
        }
        BeanUtils.copyProperties(homepageDao.selectOne(queryWrapper), homepageDTO);
        return Result.succeed(homepageDTO);
    }


    @Override
    public Result<String> getResumeByUserId(String userId) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO != null) {
            return Result.succeed(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), userDO.getResume());
        } else {
            return Result.succeed(ResultCode.NO_FOUND_DATA.getCode(), ResultCode.NO_FOUND_DATA.getMessage());
        }
    }

    @Override
    public Result<String> saveResumeByUserId(String userId, String resume) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId != null, "id", userId);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO != null) {
            userDO.setResume(resume);
            userDO.setUpdateTime(LocalDateTime.now());
            int i = userDao.updateById(userDO);
            if (i == 1) {
                return Result.succeed(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), userDO.getResume());
            } else {
                return Result.succeed(ResultCode.NO_FOUND_DATA.getCode(), ResultCode.NO_FOUND_DATA.getMessage());
            }
        } else {
            return Result.succeed(ResultCode.NO_FOUND_DATA.getCode(), ResultCode.NO_FOUND_DATA.getMessage());
        }
    }


    @Override
    public Result<String> getHeadImageByUserId(String userId) {
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO != null) {
            return Result.succeed(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), userDO.getHeadImage());
        }
        return Result.succeed(ResultCode.NO_FOUND_DATA.getCode(), ResultCode.NO_FOUND_DATA.getMessage());
    }

    @Override
    public Result<String> saveHeadImageByUserId(MultipartFile image, String userId) {
        String data = null;
        String code;
        String message;
        try {
            //上传阿里oss
            data = AliyunOss.ossFileUpload("images/" + userId + image.getOriginalFilename(), image.getInputStream());
            QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", userId);
            UserDO userDO = userDao.selectOne(queryWrapper);
            userDO.setUpdateTime(LocalDateTime.now());
            userDO.setHeadImage(data);
            userDao.updateById(userDO);
            message = ResultCode.SUCCESS.getMessage();
            code = ResultCode.SUCCESS.getCode();
        } catch (IOException e) {
            message = ResultCode.OPERATION_FAIL_D0001.getMessage();
            code = ResultCode.OPERATION_FAIL_D0001.getCode();
        }
        return Result.succeed(code, message, data);
    }

    @Override
    public Result<String> getUsernameByUserId(String userId) {
        String code;
        String message;
        String data = null;
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        UserDO userDO = userDao.selectOne(queryWrapper);
        if (userDO != null) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
            data = userDO.getUsername();
        } else {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();
        }
        return Result.succeed(code, message, data);
    }

    @Override
    public Result<String> saveUsernameByUserId(String userId, String username) {
        String code;
        String message;
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        UserDO userDO = userDao.selectOne(queryWrapper);
        userDO.setUsername(username);
        userDO.setUpdateTime(LocalDateTime.now());
        int i = userDao.updateById(userDO);
        if (i == 1) {
            code = ResultCode.SUCCESS.getCode();
            message = ResultCode.SUCCESS.getMessage();
        } else {
            code = ResultCode.USER_ERROR_A0201.getCode();
            message = ResultCode.USER_ERROR_A0201.getMessage();
        }
        return Result.succeed(code, message);
    }
}
