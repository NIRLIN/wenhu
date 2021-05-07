package org.wenhu.search.service;

import org.wenhu.search.pojo.EsAnswer;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/25
 */
public interface EsAnswerService {
    /**
     * 收缩
     *
     * @param search 关键字
     * @return 操作结果
     */
    Iterable<EsAnswer> search(String search);

    /**
     * 删除查询数据
     *
     * @return 操作结果
     */
    int deleteAllEsAnswer();

    /**
     * 保存数据
     *
     * @return 操作结果
     */
    int saveAllEsAnswer();

    /**
     * 检查最近回答
     *
     * @return 操作结果
     */
    int checkRecentEsAnswer();
}
