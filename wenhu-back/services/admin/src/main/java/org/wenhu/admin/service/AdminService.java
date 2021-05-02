package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.AdminDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */
public interface AdminService {
    HashMap<String, Object> listAdmin(Map<String, Object> objectMap);

    HashMap<String, Object> listAdminSearch(Map<String, Object> objectMap);

    HashMap<String, Object> updateAdmin(AdminDO adminDO);

    AdminDO peopleInfo(String userId);

}
