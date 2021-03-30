package org.wenhu.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.pojo.VO.AnswerVO;
import org.wenhu.common.util.Result;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Component
@FeignClient(name = "creation")
public interface CreationFeignClient {


    @PostMapping(name = "question/listQuestionByUserId", value = "question/listQuestionByUserId")
    Result<List<QuestionDO>> listQuestionByUserId(@RequestBody UserDTO userDTO);

    @PostMapping(name = "article/listArticleHeatByUserId", value = "article/listArticleHeatByUserId")
    Result<List<AnswerVO>> listArticleHeatByUserId(@RequestBody UserDTO userDTO);

    @PostMapping(name = "article/listArticleTimeByUserId", value = "article/listArticleTimeByUserId")
    Result<List<AnswerVO>> listArticleTimeByUserId(@RequestBody UserDTO userDTO);


    @PostMapping(name = "answer/listAnswerByUserId", value = "answer/listAnswerByUserId")
    Result<List<AnswerVO>> listAnswerByUserId(@RequestParam("userId") String userId,@RequestParam("type") String type);

    @PostMapping(name = "article/listArticleByUserId", value = "article/listArticleByUserId")
    Result<List<AnswerVO>> listArticleByUserId(@RequestParam("userId") String userId,@RequestParam("type") String type);
}
