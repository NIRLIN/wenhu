package org.wenhu.hot.controller;

import org.springframework.web.bind.annotation.*;
import org.wenhu.common.pojo.DO.HotDO;
import org.wenhu.common.util.Result;
import org.wenhu.hot.service.impl.HotServiceImpl;

import java.util.List;
import java.util.Map;
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

    @PostMapping(name = "listHotByClassify", path = "listHotByClassify")
    public Result<List<HotDO>> listHotByClassify(@RequestBody Map<String, Object> objectMap) {
        return hotService.listHotByClassify(objectMap);
    }


}
