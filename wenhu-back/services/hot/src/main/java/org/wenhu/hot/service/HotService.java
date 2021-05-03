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

    Set<String> listHotQuestion();

    void countQuestionHeat();

    Result<List<HotDO>> listHotByClassify(Map<String, Object> objectMap);
}
