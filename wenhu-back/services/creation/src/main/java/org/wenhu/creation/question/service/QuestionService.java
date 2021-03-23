package org.wenhu.creation.question.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.wenhu.pojo.DTO.QuestionDTO;
import org.wenhu.util.Result;

/**
 * @author NIRLIN
 * @version 1.0
 * @date 2021/3/22
 */

public interface QuestionService {
    Result<String> saveQuestion( QuestionDTO questionDTO);
}
