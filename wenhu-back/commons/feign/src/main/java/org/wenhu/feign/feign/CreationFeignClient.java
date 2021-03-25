package org.wenhu.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DO.AnswerDO;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.UserDTO;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/16
 */

@Component
@FeignClient(name = "creation")
public interface CreationFeignClient {
    @PostMapping(name = "answer/listAnswerByUserId", value = "answer/listAnswerByUserId")
    List<AnswerDO> listAnswerByUserId(@RequestBody UserDTO userDTO);

    @PostMapping(name = "article/listArticleByUserId", value = "article/listArticleByUserId")
    List<AnswerDO> listArticleByUserId(@RequestBody UserDTO userDTO);

    @PostMapping(name = "question/listQuestionByUserId", value = "question/listQuestionByUserId")
    List<QuestionDO> listQuestionByUserId(@RequestBody UserDTO userDTO);

}
