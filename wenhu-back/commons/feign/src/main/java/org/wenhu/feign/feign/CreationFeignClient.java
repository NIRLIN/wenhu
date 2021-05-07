package org.wenhu.feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.AnswerDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
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

    /**
     * 查询问题
     *
     * @param userDTO 用户信息
     * @return List<QuestionDO>
     */
    @PostMapping(name = "question/listQuestionByUserId", value = "question/listQuestionByUserId")
    Result<List<QuestionDO>> listQuestionByUserId(@RequestBody UserDTO userDTO);

    /**
     * 查询回答
     *
     * @param userId 用户id
     * @param type   回答类型
     * @return List<AnswerDTO>
     */
    @PostMapping(name = "answer/listAnswerByUserId", value = "answer/listAnswerByUserId")
    Result<List<AnswerDTO>> listAnswerByUserId(@RequestParam("userId") String userId, @RequestParam("type") String type);
}
