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
    /**
     * 分类列表
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listClassify(Map<String, Object> objectMap);

    /**
     * 搜索分类结果
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> listClassifySearch(Map<String, Object> objectMap);

    /**
     * 修改分类信息
     *
     * @param classifyDO 传入数据
     * @return 操作结果
     */
    HashMap<String, Object> updateClassify(ClassifyDO classifyDO);

    /**
     * 添加分类
     *
     * @param classifyDO 传入数据
     * @return 操作结果
     */
    Result<Object> saveClassify(ClassifyDO classifyDO);
}
