package org.wenhu.creation.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.pojo.VO.ArticleVO;
import org.wenhu.common.util.Result;
import org.wenhu.creation.article.service.impl.ArticleServiceImpl;

import java.util.List;

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
    Result<List<ArticleVO>> listArticleByUserId(@RequestParam("userId") String userId, @RequestParam("type") String type) {
        return articleService.listAnswerByUserId(userId,  type);
    }
}
