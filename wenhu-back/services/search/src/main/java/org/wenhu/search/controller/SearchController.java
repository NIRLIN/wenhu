package org.wenhu.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wenhu.common.util.Result;
import org.wenhu.search.pojo.EsAnswer;
import org.wenhu.search.service.impl.EsAnswerServiceImpl;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/4/25
 */
@RestController
public class SearchController {
    @Autowired
    private EsAnswerServiceImpl esAnswerService;

    @RequestMapping("search")
    public Result<Iterable<EsAnswer>> search(@RequestParam String search) {
        Iterable<EsAnswer> answerList = esAnswerService.search(search);
        return Result.succeed(answerList);
    }
}
