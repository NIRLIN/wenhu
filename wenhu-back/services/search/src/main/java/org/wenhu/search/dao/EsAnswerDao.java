package org.wenhu.search.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.wenhu.search.pojo.EsAnswer;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/25
 */
public interface EsAnswerDao extends ElasticsearchRepository<EsAnswer, Long> {
    List<EsAnswer> findAllByTitleContainsOrContentContains(String title, String content);

    List<EsAnswer> findAllByTitleContains(String title);

    List<EsAnswer> findAllByTitle(String title);

    int deleteAllBy();
}
