package org.wenhu.hot.service;

import java.util.Set;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/2
 */
public interface HotService<T> {

    /**
     * 获取热榜信息
     *
     * @return 返回List<T>
     */
    Set<String> listHotQuestion();

}
