package org.wenhu.creation.article.service;

import org.wenhu.common.pojo.VO.ArticleVO;
import org.wenhu.common.util.Result;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/25
 */
public interface ArticleService {

    Result<List<ArticleVO>> listAnswerByUserId(String userId, String type);
}
