package org.wenhu.creation.classify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DO.ClassifyDO;
import org.wenhu.common.util.Result;
import org.wenhu.creation.classify.service.impl.ClassifyServiceImpl;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/5/2
 */

@RequestMapping("classify")
@RestController
public class ClassifyController {

    @Autowired
    private ClassifyServiceImpl classifyService;


    @GetMapping("listClassify")
    public Result<List<ClassifyDO>> listClassify() {
        List<ClassifyDO> list = classifyService.listClassify();
        return Result.succeed(list);
    }

}
