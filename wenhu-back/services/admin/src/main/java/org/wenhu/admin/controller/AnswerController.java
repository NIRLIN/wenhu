package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.AnswerServiceImpl;
import org.wenhu.common.pojo.DO.AnswerDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */

@Controller
@RequestMapping("answer")
public class AnswerController {
    @Autowired
    private AnswerServiceImpl answerService;

    @ResponseBody
    @PostMapping("listAnswer")
    public HashMap<String, Object> listAnswer(@RequestBody Map<String, Object> objectMap) {
        return answerService.listAnswer(objectMap);
    }

    @ResponseBody
    @PostMapping("listAnswerBySearch")
    public HashMap<String, Object> listAnswerBySearch(@RequestBody Map<String, Object> objectMap) {
        return answerService.listAnswerBySearch(objectMap);
    }

    @ResponseBody
    @PostMapping("updateAnswer")
    public HashMap<String, Object> updateAnswer(@RequestBody AnswerDO answerDO) {
        return answerService.updateAnswer(answerDO);
    }
}
