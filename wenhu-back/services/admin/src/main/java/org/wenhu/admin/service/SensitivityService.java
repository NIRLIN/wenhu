package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.SensitivityDO;
import org.wenhu.common.util.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */
public interface SensitivityService {
    /**
     * 敏感词列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listSensitivity(Map<String, Object> objectMap);

    /**
     * 搜索的敏感词列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listSensitivitySearch(Map<String, Object> objectMap);

    /**
     * 修改敏感词
     *
     * @param sensitivityDO 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> updateSensitivity(SensitivityDO sensitivityDO);

    /**
     * 增加敏感词
     *
     * @param sensitivityDO 传入数据
     * @return 操作结果
     */
    Result<Object> saveSensitivity(SensitivityDO sensitivityDO);
}
