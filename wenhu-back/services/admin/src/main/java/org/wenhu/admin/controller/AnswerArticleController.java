package org.wenhu.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wenhu.admin.service.impl.AnswerArticleServiceImpl;
import org.wenhu.common.pojo.DO.AnswerArticleDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */

@Controller
@RequestMapping("answerArticle")
public class AnswerArticleController {
    @Autowired
    private AnswerArticleServiceImpl answerService;

    @ResponseBody
    @PostMapping("listAnswerArticle")
    public HashMap<String, Object> listAnswerArticle(@RequestBody Map<String, Object> objectMap) {
        return answerService.listAnswerArticle(objectMap);
    }

    @ResponseBody
    @PostMapping("listAnswerArticleBySearch")
    public HashMap<String, Object> listAnswerArticleBySearch(@RequestBody Map<String, Object> objectMap) {
        return answerService.listAnswerArticleBySearch(objectMap);
    }

    @ResponseBody
    @PostMapping("saveAnswerArticle")
    public HashMap<String, Object> saveAnswerArticle(@RequestBody AnswerArticleDO answerArticleDO) {
        return answerService.saveAnswerArticle(answerArticleDO);
    }
}
