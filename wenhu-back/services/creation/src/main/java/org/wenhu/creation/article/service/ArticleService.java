package org.wenhu.creation.article.service;

import org.wenhu.common.pojo.DTO.AnswerArticleDTO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface ArticleService {

    Result<List<AnswerArticleDTO>> listAnswerByUserId(String userId, String type);

    Result<Boolean> getUserAgreeArticle(String userId, String answerId);

    Result<HashMap<String, Object>> getUserCollectArticle(String userId, String answerId);

    Result<HashMap<String, Object>> userAgreeArticle(String userId, String answerId);

    Result<HashMap<String, Object>> userOpposeArticle(String userId, String answerId);

    Result<AnswerArticleDTO> getArticleByArticleId(String articleId);
}
