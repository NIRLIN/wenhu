package org.wenhu.hot.service;

import org.wenhu.common.pojo.DO.HotDO;
import org.wenhu.common.util.Result;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/2
 */
public interface HotService {

    /**
     * 热榜列表
     *
     * @return 操作结果
     */
    Set<String> listHotQuestion();

    /**
     * 统计问题热度
     */
    void countQuestionHeat();

    /**
     * 获取分类问题
     *
     * @param objectMap 传入数据
     * @return 操作结果
     */
    Result<List<HotDO>> listHotByClassify(Map<String, Object> objectMap);
}
