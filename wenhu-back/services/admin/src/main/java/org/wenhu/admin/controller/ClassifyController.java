package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.ClassifyServiceImpl;
import org.wenhu.common.pojo.DO.ClassifyDO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */

@Controller
@RequestMapping("classify")
public class ClassifyController {

    @Autowired
    private ClassifyServiceImpl classifyService;

    @ResponseBody
    @PostMapping("listClassify")
    public HashMap<String, Object> listClassify(@RequestBody Map<String, Object> objectMap) {
        return classifyService.listClassify(objectMap);
    }

    @ResponseBody
    @PostMapping("listClassifySearch")
    public HashMap<String, Object> listClassifySearch(@RequestBody Map<String, Object> objectMap) {
        return classifyService.listClassifySearch(objectMap);
    }

    @ResponseBody
    @PostMapping("updateClassify")
    public HashMap<String, Object> updateClassify(@RequestBody ClassifyDO classifyDO) {
        return classifyService.updateClassify(classifyDO);
    }

    @ResponseBody
    @PostMapping("saveClassify")
    public Result<Object> saveClassify(@RequestBody ClassifyDO classifyDO) {
        return classifyService.saveClassify(classifyDO);
    }

}
