package org.wenhu.creation.article.service;

import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface ArticleService {

    Result<List<AnswerArticleDTO>> listAnswerByUserId(String userId, String type);

    Result<Boolean> getUserAgreeArticle(String userId, String answerId);

    Result<Boolean> saveAgreeArticleByUserId(String userId, String answerId);

    Result<Boolean> saveOpposeArticleByUserId(String userId, String answerId);
}
