package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wenhu.admin.service.impl.AdminServiceImpl;
import org.wenhu.common.pojo.DO.AdminDO;

import javax.servlet.http.HttpSession;
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
    public HashMap<String, Object> listAdmin(@RequestBody Map<String, Object> objectMap, HttpSession session) {
        return adminService.listAdmin(objectMap, session);
    }

    @ResponseBody
    @PostMapping("listAdminSearch")
    public HashMap<String, Object> listAdminSearch(@RequestBody Map<String, Object> objectMap) {
        return adminService.listAdminSearch(objectMap);
    }

    @ResponseBody
    @PostMapping("updateAdmin")
    public HashMap<String, Object> updateAdmin(@RequestBody AdminDO adminDO) {
        return adminService.updateAdmin(adminDO);
    }

    @ResponseBody
    @PostMapping("saveAdmin")
    public Object saveAdmin(@RequestBody AdminDO adminDO) {
        return adminService.saveAdmin(adminDO);
    }

    @ResponseBody
    @GetMapping("peopleInfo")
    public AdminDO peopleInfo(HttpSession httpSession) {
        String userId = (String) httpSession.getAttribute("userId");
        return adminService.peopleInfo(userId);
    }
}
