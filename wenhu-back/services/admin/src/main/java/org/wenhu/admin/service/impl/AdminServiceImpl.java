package org.wenhu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.admin.service.AdminService;
import org.wenhu.common.pojo.DO.AdminDO;
import org.wenhu.database.dao.AdminDao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public HashMap<String, Object> listAdmin(Map<String, Object> objectMap) {
        Integer page = (Integer) objectMap.get("page");
        Integer adminBaned = (Integer) objectMap.get("adminBaned");
        QueryWrapper<AdminDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time");
        if (adminBaned == 0) {
            queryWrapper.lt("deadline_date", LocalDateTime.now());
        }
        if (adminBaned == 1) {
            queryWrapper.gt("deadline_date", LocalDateTime.now());
        }
        List<AdminDO> listAdmin = listSensitivityByPage(page, queryWrapper);
        //重置条件
        queryWrapper = new QueryWrapper<>();
        Integer listAdminCount = countSensitivity(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listAdmin", listAdmin);
        hashMap.put("listAdminCount", listAdminCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> listAdminSearch(Map<String, Object> objectMap) {
        Integer adminBaned = (Integer) objectMap.get("adminBaned");
        Integer page = (Integer) objectMap.get("page");
        String adminId = (String) objectMap.get("adminId");
        String username = (String) objectMap.get("username");
        String phoneNumber = (String) objectMap.get("phoneNumber");
        String resume = (String) objectMap.get("resume");
        QueryWrapper<AdminDO> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("update_time")
                .like(adminId != null, "id", adminId)
                .like(username != null, "username", username)
                .like(phoneNumber != null, "phone_number", phoneNumber)
                .like(resume != null, "resume", resume);
        //分辨是否封禁
        if (adminBaned == 0) {
            queryWrapper.lt("deadline_date", LocalDateTime.now());
        }
        if (adminBaned == 1) {
            queryWrapper.gt("deadline_date", LocalDateTime.now());
        }
        //分页查询
        List<AdminDO> listAdminSearch = listSensitivityByPage(page, queryWrapper);
        //查询总页数
        Integer listAdminSearchCount = countSensitivity(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("listAdminSearch", listAdminSearch);
        hashMap.put("listAdminSearchCount", listAdminSearchCount);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> saveAdmin(AdminDO adminDO) {
        adminDO.setUpdateTime(LocalDateTime.now());
        int i = adminDao.updateById(adminDO);
        HashMap<String, Object> hashMap = new HashMap<>(1);
        if (i == 1) {
            hashMap.put("saveAdminResult", true);
        } else {
            hashMap.put("saveAdminResult", false);
        }
        return hashMap;
    }


    private List<AdminDO> listSensitivityByPage(Integer page, QueryWrapper<AdminDO> queryWrapper) {
        //分页查询
        Page<AdminDO> doPage = new Page<>(page, 10);
        Page<AdminDO> selectPage = adminDao.selectPage(doPage, queryWrapper);
        return selectPage.getRecords();
    }

    private Integer countSensitivity(QueryWrapper<AdminDO> queryWrapper) {
        //查询总数
        return adminDao.selectCount(queryWrapper);
    }

}
