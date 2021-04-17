package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.PositionServiceImpl;
import org.wenhu.common.pojo.DO.PositionDO;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */

@Controller
@RequestMapping("position")
public class PositionController {
    @Autowired
    private PositionServiceImpl positionService;

    @ResponseBody
    @PostMapping("listPosition")
    public HashMap<String, Object> listPosition() {
        return positionService.listPosition();
    }

    @ResponseBody
    @PostMapping("savePosition")
    public HashMap<String, Object> savePosition(@RequestBody PositionDO positionDO) {
        return positionService.savePosition(positionDO);
    }

}
