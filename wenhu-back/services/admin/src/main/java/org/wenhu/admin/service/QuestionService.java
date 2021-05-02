package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.QuestionDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */
public interface QuestionService {
    HashMap<String, Object> listQuestion(Map<String, Object> objectMap);

    HashMap<String, Object> updateQuestion(QuestionDO questionDO);

    HashMap<String, Object> listQuestionSearch(Map<String, Object> objectMap);
}
