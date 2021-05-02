package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.SensitivityServiceImpl;
import org.wenhu.common.pojo.DO.SensitivityDO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */

@Controller
@RequestMapping("sensitivity")
public class SensitivityController {

    @Autowired
    private SensitivityServiceImpl sensitivityService;

    @ResponseBody
    @PostMapping("listSensitivity")
    public HashMap<String, Object> listSensitivity(@RequestBody Map<String, Object> objectMap) {
        return sensitivityService.listSensitivity(objectMap);
    }

    @ResponseBody
    @PostMapping("listSensitivitySearch")
    public HashMap<String, Object> listSensitivitySearch(@RequestBody Map<String, Object> objectMap) {
        return sensitivityService.listSensitivitySearch(objectMap);
    }

    @ResponseBody
    @PostMapping("updateSensitivity")
    public HashMap<String, Object> updateSensitivity(@RequestBody SensitivityDO sensitivityDO) {
        return sensitivityService.updateSensitivity(sensitivityDO);
    }

    @ResponseBody
    @PostMapping("saveSensitivity")
    public Result<Object> saveSensitivity(@RequestBody SensitivityDO sensitivityDO) {
        return sensitivityService.saveSensitivity(sensitivityDO);
    }

}
