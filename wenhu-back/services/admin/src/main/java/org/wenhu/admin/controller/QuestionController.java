package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.QuestionServiceImpl;
import org.wenhu.common.pojo.DO.QuestionDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */


@Controller
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @ResponseBody
    @PostMapping("listQuestion")
    public HashMap<String, Object> listQuestion(@RequestBody Map<String, Object> objectMap) {
        return questionService.listQuestion(objectMap);
    }

    @ResponseBody
    @PostMapping("listQuestionSearch")
    public HashMap<String, Object> listQuestionSearch(@RequestBody Map<String, Object> objectMap) {
        return questionService.listQuestionSearch(objectMap);
    }

    @ResponseBody
    @PostMapping("saveQuestion")
    public HashMap<String, Object> saveQuestion(@RequestBody QuestionDO questionDO) {
        return questionService.saveQuestion(questionDO);
    }


}
