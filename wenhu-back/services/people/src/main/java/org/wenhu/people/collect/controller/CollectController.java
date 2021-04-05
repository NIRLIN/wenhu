package org.wenhu.people.collect.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.util.Result;
import org.wenhu.people.collect.service.impl.CollectServiceImpl;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping(name = "userCollectAnswer", value = "userCollectAnswer")
    Result<HashMap<String, Object>> userCollectAnswer(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String answerId = (String) objectMap.get("answerId");
        return collectService.userCollectAnswer(userId, answerId);
    }
}
