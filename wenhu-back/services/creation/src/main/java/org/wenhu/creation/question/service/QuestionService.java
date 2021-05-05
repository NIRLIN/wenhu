package org.wenhu.creation.question.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.QuestionLogDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;

import java.util.List;
import java.util.Map;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/22
 */

public interface QuestionService {
    Result<String> saveQuestion(QuestionDO questionDO, String menderId);

    Result<QuestionDO> getQuestionById(String id);

    Result<List<QuestionDO>> listQuestionByUserId(@RequestBody UserDTO userDTO);

    Result<Integer> userFollowQuestion(Map<String, Object> objectMap);

    Result<Integer> followQuestion(Map<String, Object> objectMap);

    Result<List<QuestionLogDTO>> listQuestionLog(Map<String, Object> objectMap);

    Result<Integer> updateQuestion(Map<String, Object> objectMap);
}
