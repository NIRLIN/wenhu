package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.AnswerArticleDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */
public interface AnswerArticleService {
    HashMap<String, Object> listAnswerArticle(Map<String, Object> objectMap);

    HashMap<String, Object> listAnswerArticleBySearch(Map<String, Object> objectMap);

    HashMap<String, Object> saveAnswerArticle(AnswerArticleDO answerArticleDO);

}
