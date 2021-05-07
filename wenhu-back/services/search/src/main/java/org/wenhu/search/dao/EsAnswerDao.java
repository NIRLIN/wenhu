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
    /**
     * 查询数据
     *
     * @param title   title
     * @param content content
     * @return 操作结果
     */
    List<EsAnswer> findAllByTitleContainsOrContentContains(String title, String content);


    /**
     * 删除
     *
     * @return 操作结果
     */
    int deleteAllBy();
}
