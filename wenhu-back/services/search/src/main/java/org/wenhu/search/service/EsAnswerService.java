package org.wenhu.search.service;

import org.wenhu.search.pojo.EsAnswer;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/25
 */
public interface EsAnswerService {
    Iterable<EsAnswer> search(String search);

    int deleteAllEsAnswer();

    int saveAllEsAnswer();

    int checkRecentEsAnswer();
}
