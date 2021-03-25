package org.wenhu.creation.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wenhu.common.pojo.DO.ArticleDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.creation.article.service.ArticleService;
import org.wenhu.database.dao.ArticleDao;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<ArticleDO> listArticleByUserId(UserDTO userDTO) {
        //获取文章
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("user_id", userDTO.getId());
        return articleDao.selectByMap(hashMap);
    }
}
