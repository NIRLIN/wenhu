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

    HashMap<String, Object> listUserNoBanned(Map<String, Object> objectMap);

    HashMap<String, Object> listUserIsBanned(Map<String, Object> objectMap);

    Boolean saveUser(UserDO userDO);

    HashMap<String, Object> listUserNoBanedSearch(Map<String, Object> objectMap);

    HashMap<String, Object> listUserIsBanedSearch(Map<String, Object> objectMap);
}
