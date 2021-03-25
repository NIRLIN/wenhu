package org.wenhu.creation.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.pojo.DO.ArticleDO;
import org.wenhu.common.pojo.DTO.UserDTO;
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
    List<ArticleDO> listArticleByUserId(@RequestBody UserDTO userDTO) {
        return articleService.listArticleByUserId(userDTO);
    }
}
