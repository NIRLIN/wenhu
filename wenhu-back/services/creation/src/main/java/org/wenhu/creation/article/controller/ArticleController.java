package org.wenhu.creation.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;
import org.wenhu.creation.article.service.impl.ArticleServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */

@RestController
@RequestMapping(name = "article", value = "article")
public class ArticleController {
    @Autowired
    private ArticleServiceImpl articleService;


    @PostMapping(name = "listArticleByUserId", value = "listArticleByUserId")
    Result<List<AnswerArticleDTO>> listArticleByUserId(@RequestParam("userId") String userId, @RequestParam("type") String type) {
        return articleService.listAnswerByUserId(userId, type);
    }

    @PostMapping(name = "getUserAgreeArticle", value = "getUserAgreeArticle")
    Result<Boolean> getUserAgreeArticle(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String answerId = (String) objectMap.get("answerId");
        return articleService.getUserAgreeArticle(userId, answerId);
    }

    @PostMapping(name = "saveAgreeArticleByUserId", value = "saveAgreeArticleByUserId")
    Result<Boolean> saveAgreeArticleByUserId(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String answerId = (String) objectMap.get("answerId");
        return articleService.saveAgreeArticleByUserId(userId, answerId);
    }
    @PostMapping(name = "saveOpposeArticleByUserId", value = "saveOpposeArticleByUserId")
    Result<Boolean> saveOpposeArticleByUserId(@RequestBody Map<String, Object> objectMap) {
        String userId = (String) objectMap.get("userId");
        String answerId = (String) objectMap.get("answerId");
        return articleService.saveOpposeArticleByUserId(userId, answerId);
    }
}
