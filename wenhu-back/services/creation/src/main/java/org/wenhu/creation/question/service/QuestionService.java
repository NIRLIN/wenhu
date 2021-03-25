package org.wenhu.creation.question.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.common.pojo.DO.QuestionDO;
import org.wenhu.common.pojo.DTO.QuestionDTO;
import org.wenhu.common.pojo.DTO.UserDTO;
import org.wenhu.common.util.Result;

import java.util.List;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/22
 */

public interface QuestionService {
    Result<String> saveQuestion(QuestionDTO questionDTO, String menderId);

    Result<QuestionDTO> getQuestionById(String id);

    List<QuestionDO> listQuestionByUserId(@RequestBody UserDTO userDTO);
}
