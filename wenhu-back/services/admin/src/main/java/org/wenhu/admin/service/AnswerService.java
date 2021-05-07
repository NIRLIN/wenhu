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
    /**
     * 查询回答列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listAnswer(Map<String, Object> objectMap);

    /**
     * 查询回答列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listAnswerBySearch(Map<String, Object> objectMap);

    /**
     * 修改回答信息
     *
     * @param answerDO 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> updateAnswer(AnswerDO answerDO);

}
