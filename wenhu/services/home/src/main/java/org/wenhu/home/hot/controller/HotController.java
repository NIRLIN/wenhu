package org.wenhu.home.hot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.home.hot.service.impl.HotServiceImpl;
import org.wenhu.util.Result;

import java.util.Set;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/2/4
 */
@RestController
@RequestMapping(name = "hot", value = "hot")
public class HotController {
    private final HotServiceImpl hotService;

    public HotController(HotServiceImpl hotService) {
        this.hotService = hotService;
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(name = "listHotQuestion", path = "listHotQuestion")
    public Result<Set<String>> listHotQuestion() {
        Set<String> stringSet = hotService.listHotQuestion();
        return Result.succeed(stringSet);
    }


}
