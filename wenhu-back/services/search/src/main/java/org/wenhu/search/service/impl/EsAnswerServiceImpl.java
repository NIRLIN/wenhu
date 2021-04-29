package org.wenhu.search.service.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.wenhu.search.dao.AnswerDao;
import org.wenhu.search.dao.EsAnswerDao;
import org.wenhu.search.pojo.EsAnswer;
import org.wenhu.search.service.EsAnswerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/25
 */
@Service
public class EsAnswerServiceImpl implements EsAnswerService {
    @Autowired
    private EsAnswerDao esAnswerDao;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private AnswerDao answerDao;

    @Override
    public Iterable<EsAnswer> search(String search) {
        //根据一个值查询多个字段  并高亮显示  这里的查询是取并集，即多个字段只需要有一个字段满足即可
        //需要查询的字段
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", search))
                .should(QueryBuilders.matchQuery("content", search));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("title")
                        , new HighlightBuilder.Field("content"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<EsAnswer> esAnswerSearchHits = elasticsearchRestTemplate.search(searchQuery, EsAnswer.class);
        //得到查询返回的内容
        List<SearchHit<EsAnswer>> searchHits = esAnswerSearchHits.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<EsAnswer> esAnswerList = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<EsAnswer> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setTitle(highlightFields.get("title") == null ? searchHit.getContent().getTitle() : highlightFields.get("title").get(0));
            searchHit.getContent().setContent(highlightFields.get("content") == null ? searchHit.getContent().getContent() : highlightFields.get("content").get(0));
            //放到实体类中
            esAnswerList.add(searchHit.getContent());
        }
        return esAnswerList;

    }


    @Override
    public int deleteAllEsAnswer() {
        return esAnswerDao.deleteAllBy();
    }

    @Override
    public int saveAllEsAnswer() {
        List<EsAnswer> answerList = answerDao.listAnswer();
        for (EsAnswer esAnswer : answerList) {
            EsAnswer save = esAnswerDao.save(esAnswer);
        }
        return 1;
    }

    @Override
    public int checkRecentEsAnswer() {
        return 0;
    }

}
