package org.wenhu.people.collect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.util.Result;
import org.wenhu.people.collect.service.impl.CollectServiceImpl;

import java.util.HashMap;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@RestController
@RequestMapping(name = "collect", value = "collect")
public class CollectController {
    @Autowired
    private CollectServiceImpl collectService;

    public Result<HashMap<String, Object>> listCollectByUserId(String userId) {
        return collectService.collectService(userId);
    }
}
