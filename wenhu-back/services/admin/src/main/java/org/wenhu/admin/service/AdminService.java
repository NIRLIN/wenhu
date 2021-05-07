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
    /**
     * 查询管理员列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listAdmin(Map<String, Object> objectMap);

    /**
     * 搜索管理员列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listAdminSearch(Map<String, Object> objectMap);

    /**
     * 修改管理员
     *
     * @param adminDO 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> updateAdmin(AdminDO adminDO);

    /**
     * 查询管理员信息
     *
     * @param userId id
     * @return 操作结果
     */
    AdminDO peopleInfo(String userId);

}
