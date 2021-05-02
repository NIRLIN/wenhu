package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.ClassifyDO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */
public interface ClassifyService {
    HashMap<String, Object> listClassify(Map<String, Object> objectMap);

    HashMap<String, Object> listClassifySearch(Map<String, Object> objectMap);

    HashMap<String, Object> updateClassify(ClassifyDO classifyDO);

    Result<Object> saveClassify(ClassifyDO classifyDO);
}
