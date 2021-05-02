package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.AnswerDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/16
 */
public interface AnswerService {
    HashMap<String, Object> listAnswer(Map<String, Object> objectMap);

    HashMap<String, Object> listAnswerBySearch(Map<String, Object> objectMap);

    HashMap<String, Object> updateAnswer(AnswerDO answerDO);

}
