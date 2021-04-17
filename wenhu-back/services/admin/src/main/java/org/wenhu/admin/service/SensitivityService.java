package org.wenhu.admin.service;

import org.wenhu.common.pojo.DO.SensitivityDO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/17
 */
public interface SensitivityService {
    HashMap<String, Object> listSensitivity(Map<String, Object> objectMap);

    HashMap<String, Object> listSensitivitySearch(Map<String, Object> objectMap);

    HashMap<String, Object> saveSensitivity(SensitivityDO sensitivityDO);
}
