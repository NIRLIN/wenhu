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
    /**
     * 问题列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listQuestion(Map<String, Object> objectMap);

    /**
     * 修改问题
     *
     * @param questionDO 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> updateQuestion(QuestionDO questionDO);

    /**
     * 搜索后的问题列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listQuestionSearch(Map<String, Object> objectMap);
}
