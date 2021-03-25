package org.wenhu.creation.article.service;

import org.wenhu.common.pojo.DO.ArticleDO;
import org.wenhu.common.pojo.DTO.UserDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface ArticleService {
    List<ArticleDO> listArticleByUserId(UserDTO userDTO);
}
