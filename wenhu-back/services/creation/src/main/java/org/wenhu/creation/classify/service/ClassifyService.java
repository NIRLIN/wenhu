package org.wenhu.creation.classify.service;

import org.wenhu.common.pojo.DO.ClassifyDO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/5/2
 */
public interface ClassifyService {
    /**
     * 获取分类列表
     *
     * @return 操作结果
     */
    List<ClassifyDO> listClassify();
}
