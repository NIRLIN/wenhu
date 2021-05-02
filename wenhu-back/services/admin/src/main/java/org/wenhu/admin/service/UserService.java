package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.UserDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */
public interface UserService {

    /**
     * 登录验证
     *
     * @param phoneNumber 手机号
     * @param password    密码
     * @return 布尔
     */
    UserDO userLogin(String phoneNumber, String password);

    /**
     * 未封禁用户
     *
     * @param objectMap 分页信息
     * @return 用户列表、列表总数
     */
    HashMap<String, Object> listUserNoBanned(Map<String, Object> objectMap);

    /**
     * 封禁用户
     *
     * @param objectMap 分页信息
     * @return 用户列表、列表总数
     */
    HashMap<String, Object> listUserIsBanned(Map<String, Object> objectMap);

    /**
     * 保存用户信息修改
     *
     * @param userDO 用户信息
     * @return 操作结果
     */
    Boolean updateUser(UserDO userDO);

    /**
     * 未封禁用户信息搜索
     *
     * @param objectMap 搜索关键字段
     * @return 用户列表、列表总数
     */
    HashMap<String, Object> listUserNoBanedSearch(Map<String, Object> objectMap);

    /**
     * 封禁用户信息搜索
     *
     * @param objectMap 搜索关键字段
     * @return 用户列表、列表总数
     */
    HashMap<String, Object> listUserIsBanedSearch(Map<String, Object> objectMap);

    String changePassword(String userId, String oldPassword, String oneNewPassword);

}
