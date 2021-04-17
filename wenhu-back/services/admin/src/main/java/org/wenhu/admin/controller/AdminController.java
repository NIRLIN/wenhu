package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.AdminServiceImpl;
import org.wenhu.common.pojo.DO.AdminDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;


    @ResponseBody
    @PostMapping("listAdmin")
    public HashMap<String, Object> listAdmin(@RequestBody Map<String, Object> objectMap) {
        return adminService.listAdmin(objectMap);
    }

    @ResponseBody
    @PostMapping("listAdminSearch")
    public HashMap<String, Object> listAdminSearch(@RequestBody Map<String, Object> objectMap) {
        return adminService.listAdminSearch(objectMap);
    }

    @ResponseBody
    @PostMapping("saveAdmin")
    public HashMap<String, Object> saveAdmin(@RequestBody AdminDO adminDO) {
        return adminService.saveAdmin(adminDO);
    }
}
